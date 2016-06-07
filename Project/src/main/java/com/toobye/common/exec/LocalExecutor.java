/*
 * Copyright 2014 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2014/02/28.
 * 
 */
package com.toobye.common.exec;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.Executor;
import org.apache.commons.exec.OS;
import org.apache.commons.exec.PumpStreamHandler;

import com.toobye.common.base.Exceptions;
import com.toobye.common.concurrent.TimeSlice;
import com.toobye.common.io.Files;
import com.toobye.common.string.StringArray;

/**
 * <pre> 本地执行器.
 * 脚本中星号被转义的处理方式：
 * set -f 或者 GLOBIGNORE=*
 * 
 * 内容定位：find . -name \* -print0 | xargs -0 grep -n -H google
 * 显示匹配前后行: grep -C 5 google
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2014/02/28  huangys  v1.0      Create
 * </pre>
 * 
 */
public final class LocalExecutor {
	
	private LocalExecutor() { }
	
	/**
	 * <pre> 返回是否为Windows操作系统.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/02/28  huangys  Create
	 * </pre>
	 * 
	 * @return 是否为Windows操作系统
	 */
	@Nonnull
	public static boolean isFamilyWindows() {
		return OS.isFamilyWindows();
	}
	
	/**
	 * <pre> 返回是否为Unix操作系统.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/02/28  huangys  Create
	 * </pre>
	 * 
	 * @return 是否为Unix操作系统
	 */
	@Nonnull
	public static boolean isFamilyUnix() {
		return OS.isFamilyUnix();
	}
	
	/**
	 * <pre> 返回是否为Windows脚本.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/02/28  huangys  Create
	 * </pre>
	 * 
	 * @param scriptFile 脚本名称
	 * @return 是否为Windows脚本
	 */
	@Nonnull
	public static boolean isWindowsScriptFile(@Nonnull final String scriptFile) {
		return scriptFile.toUpperCase().endsWith(".BAT");
	}
	
	/**
	 * <pre> 返回是否为Unix脚本.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/02/28  huangys  Create
	 * </pre>
	 * 
	 * @param scriptFile 脚本名称
	 * @return 是否为Unix脚本
	 */
	@Nonnull
	public static boolean isUnixScriptFile(@Nonnull final String scriptFile) {
		return scriptFile.toUpperCase().endsWith(".SH");
	}
	
	/**
	 * <pre> 根据不同的操作系统添加不同的脚本文件后缀.
	 * AB -> AB.bat or AB.sh
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/08/22  huangys  Create
	 * </pre>
	 * 
	 * @param scriptName 脚本名称/基本名
	 * @return 完整脚本名称
	 */
	@Nonnull
	public static String getScriptFile(@Nonnull final String scriptName) {
		if (isFamilyWindows()) {
			if (isWindowsScriptFile(scriptName)) {
				return scriptName;
			} else if (isUnixScriptFile(scriptName)) {
				throw new RuntimeException("Suffix of script file is wrong.");
			} else {
				return scriptName + ".bat";
			}
		} else {
			if (isWindowsScriptFile(scriptName)) {
				throw new RuntimeException("Suffix of script file is wrong.");
			} else if (isUnixScriptFile(scriptName)) {
				return scriptName;
			} else {
				return scriptName + ".sh";
			}
		}
	}
	
	/**
	 * <pre> 脚本文件转为可执行命令内容.
	 * 
	 * windows -> cmd /c scriptFile
	 * unix -> sh scriptFile
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/08/11  huangys  Create
	 * </pre>
	 * 
	 * @param scriptFile 脚本名称
	 * @return 可执行命令内容
	 */
	@Nonnull
	public static String getScriptFileCommandUsingOS(@Nonnull final String scriptFile) {
		if (isFamilyWindows()) {
			return "cmd /c " + scriptFile;
		} else {
			return "sh " + scriptFile;
		}
	}
	
	/**
	 * <pre> 脚本文件转为可执行命令内容.
	 * 
	 * Script.bat -> cmd /c Script.bat
	 * Script.sh -> sh Script.sh
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/03/01  huangys  Create
	 * </pre>
	 * 
	 * @param scriptFile 脚本名称
	 * @return 可执行命令内容
	 */
	@Nonnull
	public static String getScriptFileCommandUsingSuffix(@Nonnull final String scriptFile) {
		if (isWindowsScriptFile(scriptFile)) {
			return "cmd /c " + scriptFile;
		} else {
			return "sh " + scriptFile;
		}
	}
	
	/**
	 * <pre> 执行脚本文件.
	 * 依据操作系统生成执行脚本命令。
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/02/28  huangys  Create
	 * </pre>
	 * 
	 * @param scriptFile 脚本名称
	 * @return 执行结果
	 * @see LocalExecutor#getScriptFileCommandUsingOS(String)
	 */
	@Nonnull
	public static ExecResult execScriptFile(@Nonnull final String scriptFile) {
		return execScriptFile(scriptFile, null, (TimeSlice) null);
	}
	
	/**
	 * <pre> 执行脚本文件.
	 * 依据操作系统生成执行脚本命令。
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/02/28  huangys  Create
	 * </pre>
	 * 
	 * @param scriptFile 脚本名称
	 * @param logFile 日志文件名称
	 * @return 执行结果
	 * @see LocalExecutor#getScriptFileCommandUsingOS(String)
	 */
	@Nonnull
	public static ExecResult execScriptFile(@Nonnull final String scriptFile, @Nonnull final String logFile) {
		return execScriptFile(scriptFile, null, logFile, (TimeSlice) null);
	}
	
	/**
	 * <pre> 执行脚本文件.
	 * 依据操作系统生成执行脚本命令。
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/02/28  huangys  Create
	 * </pre>
	 * 
	 * @param scriptFile 脚本名称
	 * @param params 参数集合
	 * @return 执行结果
	 * @see LocalExecutor#getScriptFileCommandUsingOS(String)
	 */
	@Nonnull
	public static ExecResult execScriptFile(@Nonnull final String scriptFile, @Nullable final String[] params) {
		return execScriptFile(scriptFile, params, (TimeSlice) null);
	}
	
	/**
	 * <pre> 执行脚本文件.
	 * 依据操作系统生成执行脚本命令。
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/02/28  huangys  Create
	 * </pre>
	 * 
	 * @param scriptFile 脚本名称
	 * @param params 参数集合
	 * @param logFile 日志文件名称
	 * @return 执行结果
	 * @see LocalExecutor#getScriptFileCommandUsingOS(String)
	 */
	@Nonnull
	public static ExecResult execScriptFile(@Nonnull final String scriptFile, @Nullable final String[] params, @Nonnull final String logFile) {
		return execScriptFile(scriptFile, params, logFile, (TimeSlice) null);
	}
	
	/**
	 * <pre> 执行脚本文件.
	 * 依据操作系统生成执行脚本命令。
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/03/01  huangys  Create
	 * </pre>
	 * 
	 * @param scriptFile 脚本名称
	 * @param params 参数集合
	 * @param timeout 超时设置
	 * @return 执行结果
	 * @see LocalExecutor#getScriptFileCommandUsingOS(String)
	 */
	@Nonnull
	public static ExecResult execScriptFile(@Nonnull final String scriptFile, @Nullable final String[] params, @Nullable final TimeSlice timeout) {
		return execCommand(getScriptFileCommandUsingOS(scriptFile), params, timeout);
	}
	
	/**
	 * <pre> 执行脚本文件.
	 * 依据操作系统生成执行脚本命令。
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/03/01  huangys  Create
	 * </pre>
	 * 
	 * @param scriptFile 脚本名称
	 * @param params 参数集合
	 * @param logFile 日志文件名称
	 * @param timeout 超时设置
	 * @return 执行结果
	 * @see LocalExecutor#getScriptFileCommandUsingOS(String)
	 */
	@Nonnull
	public static ExecResult execScriptFile(@Nonnull final String scriptFile, @Nullable final String[] params, @Nonnull final String logFile, @Nullable final TimeSlice timeout) {
		return execCommand(getScriptFileCommandUsingOS(scriptFile), params, logFile, timeout);
	}
	
	/**
	 * <pre> 执行命令行.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/02/28  huangys  Create
	 * </pre>
	 * 
	 * @param cmd 命令内容
	 * @return 执行结果
	 */
	@Nonnull
	public static ExecResult execCommand(@Nonnull final String cmd) {
		return execCommand(cmd, null, (TimeSlice) null);
	}
	
	/**
	 * <pre> 执行命令行.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/02/28  huangys  Create
	 * </pre>
	 * 
	 * @param cmd 命令内容
	 * @param logFile 日志文件名称
	 * @return 执行结果
	 */
	@Nonnull
	public static ExecResult execCommand(@Nonnull final String cmd, @Nonnull final String logFile) {
		return execCommand(cmd, null, logFile, (TimeSlice) null);
	}
	
	/**
	 * <pre> 执行命令行.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/02/28  huangys  Create
	 * </pre>
	 * 
	 * @param cmd 命令内容
	 * @param params 参数集合
	 * @return 执行结果
	 */
	@Nonnull
	public static ExecResult execCommand(@Nonnull final String cmd, @Nullable final String[] params) {
		return execCommand(cmd, params, (TimeSlice) null);
	}
	
	/**
	 * <pre> 执行命令行.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/02/28  huangys  Create
	 * </pre>
	 * 
	 * @param cmd 命令内容
	 * @param params 参数集合
	 * @param logFile 日志文件
	 * @return 执行结果
	 */
	@Nonnull
	public static ExecResult execCommand(@Nonnull final String cmd, @Nullable final String[] params, @Nonnull final String logFile) {
		return execCommand(cmd, params, logFile, (TimeSlice) null);
	}
	
	/**
	 * <pre> 执行命令行.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/02/28  huangys  Create
	 * </pre>
	 * 
	 * @param cmd 命令内容
	 * @param params 参数集合
	 * @param timeout 超时设置
	 * @return 执行结果
	 */
	@Nonnull
	public static ExecResult execCommand(@Nonnull final String cmd, @Nullable final String[] params, @Nullable final TimeSlice timeout) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             ByteArrayOutputStream err = new ByteArrayOutputStream();) {
    		return execCommand(cmd, params, out, err, timeout);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <pre> 执行命令行.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/02/28  huangys  Create
	 * </pre>
	 * 
	 * @param cmd 命令内容
	 * @param params 参数集合
	 * @param logFile 日志文件名称
	 * @param timeout 超时设置
	 * @return 执行结果
	 */
	@Nonnull
	public static ExecResult execCommand(@Nonnull final String cmd, @Nullable final String[] params, @Nonnull final String logFile, @Nullable final TimeSlice timeout) {
		try (FileOutputStream fout = new FileOutputStream(logFile);) {
			return execCommand(cmd, params, fout, fout, timeout);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <pre> 执行命令行.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/02/28  huangys  Create
	 * </pre>
	 * 
	 * @param cmd 命令内容
	 * @param params 参数集合
	 * @param out 输出流
	 * @param err 错误输出流
	 * @param timeout 超时设置
	 * @return 执行结果
	 */
	@Nonnull
	public static ExecResult execCommand(@Nonnull final String cmd, @Nullable final String[] params, @Nonnull final OutputStream out, @Nonnull final OutputStream err, @Nullable final TimeSlice timeout) {
		return execCommand(cmd, params, out, err, timeout, null);
	}
	
	/**
	 * <pre> 执行命令行.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/02/28  huangys  Create
	 * </pre>
	 * 
	 * @param cmd 命令内容
	 * @param params 参数集合
	 * @param out 输出流
	 * @param err 错误输出流
	 * @param timeout 超时设置
	 * @param charset 字符集
	 * @return 执行结果
	 */
	@Nonnull
	public static ExecResult execCommand(@Nonnull final String cmd, @Nullable final String[] params, @Nonnull final OutputStream out, @Nonnull final OutputStream err, @Nullable final TimeSlice timeout, @Nullable final String charset) {
		// 设置命令
		CommandLine cmdLine = CommandLine.parse(cmd);
		// 加入参数
		if (params != null) {
			for (String param : params) {
				// 避免双引号带来的参数传入问题
				cmdLine.addArgument(param, false);
			}
		}
		// 加入变量映射
		// if (varMap != null) {
		// 	cmdLine.setSubstitutionMap(varMap);
		// }
		Executor executor = new DefaultExecutor();
		// 设置输出
        PumpStreamHandler sh = new PumpStreamHandler(out, err);
        executor.setStreamHandler(sh);
		// 超时设置
        if (timeout != null && timeout.getValue() != 0) {
        	ExecuteWatchdog watchdog = new ExecuteWatchdog(timeout.toMillis());
    		executor.setWatchdog(watchdog);
		}
		// 设置执行Handler
		DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
		
		try {
			// 开始执行
			executor.execute(cmdLine, resultHandler);
			// 等待执行完成
			resultHandler.waitFor();
		} catch (Exception e) {
			try {
				err.write(Exceptions.getStackTrace(e).getBytes());
			} catch (Exception e2) { } // Do Nothing
		}
		// 记录执行结果
		ExecResult ret = new ExecResult();
		ret.setCmd(cmdLine.toString());
		ret.setResult(resultHandler.getExitValue());
		if (out instanceof ByteArrayOutputStream) {
			if (charset == null) {
				ret.setOutMsg(out.toString());
				ret.setErrMsg(err.toString());
			} else {
				try {
					ret.setOutMsg(((ByteArrayOutputStream) out).toString(charset));
					ret.setErrMsg(((ByteArrayOutputStream) err).toString(charset));
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		} else {
			ret.setOutMsg("See log file for more Infomation.");
			ret.setErrMsg("See log file for more Infomation.");
		}
		return ret;
	}
	
	/**
	 * <pre> 执行脚本文件，续写日志文件.
	 * 依据操作系统生成执行脚本命令。
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/02/28  huangys  Create
	 * </pre>
	 * 
	 * @param scriptFile 脚本名称
	 * @param logFile 日志文件名称
	 * @return 执行结果
	 * @see LocalExecutor#getScriptFileCommandUsingOS(String)
	 */
	@Nonnull
	public static ExecResult execScriptFileApped(@Nonnull final String scriptFile, @Nonnull final String logFile) {
		return execScriptFileApped(scriptFile, null, logFile, (TimeSlice) null);
	}
	
	/**
	 * <pre> 执行脚本文件，续写日志文件.
	 * 依据操作系统生成执行脚本命令。
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/02/28  huangys  Create
	 * </pre>
	 * 
	 * @param scriptFile 脚本名称
	 * @param params 参数集合
	 * @param logFile 日志文件名称
	 * @return 执行结果
	 * @see LocalExecutor#getScriptFileCommandUsingOS(String)
	 */
	@Nonnull
	public static ExecResult execScriptFileApped(@Nonnull final String scriptFile, @Nullable final String[] params, @Nonnull final String logFile) {
		return execScriptFileApped(scriptFile, params, logFile, (TimeSlice) null);
	}
	
	/**
	 * <pre> 执行脚本文件，续写日志文件.
	 * 依据操作系统生成执行脚本命令。
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/03/01  huangys  Create
	 * </pre>
	 * 
	 * @param scriptFile 脚本名称
	 * @param params 参数集合
	 * @param logFile 日志文件名称
	 * @param timeout 超时设置
	 * @return 执行结果
	 * @see LocalExecutor#getScriptFileCommandUsingOS(String)
	 */
	@Nonnull
	public static ExecResult execScriptFileApped(@Nonnull final String scriptFile, @Nullable final String[] params, @Nonnull final String logFile, @Nullable final TimeSlice timeout) {
		return execCommandAppend(getScriptFileCommandUsingOS(scriptFile), params, logFile, timeout);
	}
	
	/**
	 * <pre> 执行命令行，续写日志文件.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/02/28  huangys  Create
	 * </pre>
	 * 
	 * @param cmd 命令内容
	 * @param logFile 日志文件名称
	 * @return 执行结果
	 */
	@Nonnull
	public static ExecResult execCommandAppend(@Nonnull final String cmd, @Nonnull final String logFile) {
		return execCommandAppend(cmd, null, logFile, (TimeSlice) null);
	}
	
	/**
	 * <pre> 执行命令行，续写日志文件.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/02/28  huangys  Create
	 * </pre>
	 * 
	 * @param cmd 命令内容
	 * @param params 参数集合
	 * @param logFile 日志文件
	 * @return 执行结果
	 */
	@Nonnull
	public static ExecResult execCommandAppend(@Nonnull final String cmd, @Nullable final String[] params, @Nonnull final String logFile) {
		return execCommandAppend(cmd, params, logFile, (TimeSlice) null);
	}
	
	/**
	 * <pre> 执行命令行，续写日志文件.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/02/28  huangys  Create
	 * </pre>
	 * 
	 * @param cmd 命令内容
	 * @param params 参数集合
	 * @param logFile 日志文件名称
	 * @param timeout 超时设置
	 * @return 执行结果
	 */
	@Nonnull
	public static ExecResult execCommandAppend(@Nonnull final String cmd, @Nullable final String[] params, @Nonnull final String logFile, @Nullable final TimeSlice timeout) {
		try (FileOutputStream fout = new FileOutputStream(logFile, true);) {
			return execCommand(cmd, params, fout, fout, timeout);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <pre> 用Bash方式执行命令行，这样可以利用管道等复杂命令.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/02/28  huangys  Create
	 * </pre>
	 * 
	 * @param cmd 命令内容
	 * @return 执行结果
	 */
	@Nonnull
	public static ExecResult execBashCommand(@Nonnull final String cmd) {
	    try (ByteArrayOutputStream out = new ByteArrayOutputStream();
	         ByteArrayOutputStream err = new ByteArrayOutputStream();) {
	    	 return execBashCommand(cmd, out, err, (TimeSlice) null);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <pre> 用Bash方式执行命令行，这样可以利用管道等复杂命令.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/02/28  huangys  Create
	 * </pre>
	 * 
	 * @param cmd 命令内容
	 * @param logFile 日志文件名称
	 * @return 执行结果
	 */
	@Nonnull
	public static ExecResult execBashCommand(@Nonnull final String cmd, @Nonnull final String logFile) {
		return execBashCommand(cmd, logFile, (TimeSlice) null);
	}
	
	/**
	 * <pre> 用Bash方式执行命令行，这样可以利用管道等复杂命令.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/02/28  huangys  Create
	 * </pre>
	 * 
	 * @param cmd 命令内容
	 * @param logFile 日志文件名称
	 * @param timeout 超时设置
	 * @return 执行结果
	 */
	@Nonnull
	public static ExecResult execBashCommand(@Nonnull final String cmd, @Nonnull final String logFile, @Nullable final TimeSlice timeout) {
		try (FileOutputStream fout = new FileOutputStream(logFile);) {
			return execBashCommand(cmd, fout, fout, timeout);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <pre> 用Bash方式执行命令行，这样可以利用管道等复杂命令.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/27  huangys  Create
	 * </pre>
	 * 
	 * @param cmd 命令内容
	 * @param out 输出流
	 * @param err 错误输出流
	 * @param timeout 超时设置
	 * @return 执行结果
	 */
	public static ExecResult execBashCommand(@Nonnull final String cmd, @Nonnull final OutputStream out, @Nonnull final OutputStream err, @Nullable final TimeSlice timeout) {
		return execCommand("/bin/bash", new String[]{"-c", cmd}, out, err, timeout);
	}
	
	/**
	 * <pre> 用Bash方式执行命令行，这样可以利用管道等复杂命令，续写日志文件.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/02/28  huangys  Create
	 * </pre>
	 * 
	 * @param cmd 命令内容
	 * @param logFile 日志文件名称
	 * @return 执行结果
	 */
	@Nonnull
	public static ExecResult execBashCommandAppend(@Nonnull final String cmd, @Nonnull final String logFile) {
		return execBashCommandAppend(cmd, logFile, (TimeSlice) null);
	}
	
	/**
	 * <pre> 用Bash方式执行命令行，这样可以利用管道等复杂命令，续写日志文件.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/02/28  huangys  Create
	 * </pre>
	 * 
	 * @param cmd 命令内容
	 * @param logFile 日志文件名称
	 * @param timeout 超时设置
	 * @return 执行结果
	 */
	@Nonnull
	public static ExecResult execBashCommandAppend(@Nonnull final String cmd, @Nonnull final String logFile, @Nullable final TimeSlice timeout) {
		try (FileOutputStream fout = new FileOutputStream(logFile, true);) {
			return execBashCommand(cmd, fout, fout, timeout);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <pre> 执行命令集.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/09/12  huangys  Create
	 * </pre>
	 * 
	 * @param commands 命令集
     * @return 执行结果
	 */
	public static ExecResult execCommands(@Nonnull final String[] commands) {
	    try (ByteArrayOutputStream out = new ByteArrayOutputStream();
	         ByteArrayOutputStream err = new ByteArrayOutputStream();) {
	    	 return execCommands(commands, out, err);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <pre> 执行命令集.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/09/12  huangys  Create
	 * </pre>
	 * 
	 * @param commands 命令集
     * @param logFile 日志文件
     * @return 执行结果
	 */
	public static ExecResult execCommands(@Nonnull final String[] commands, @Nonnull final String logFile) {
		try (FileOutputStream fout = new FileOutputStream(logFile);) {
			return execCommands(commands, fout, fout);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
     * <pre> 执行命令集，续写日志文件.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/09/12  huangys  Create
	 * </pre>
	 * 
	 * @param commands 命令集
     * @param logFile 日志文件
     * @return 执行结果
	 */
	public static ExecResult execCommandsAppend(@Nonnull final String[] commands, @Nonnull final String logFile) {
		try (FileOutputStream fout = new FileOutputStream(logFile, true);) {
			return execCommands(commands, fout, fout);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
    /**
     * <pre> 执行命令集.
     * 从apache-common-exec中拷贝。
     * 
     * Modification History:
     * Date        Author   Action
     * 2014/09/12  huangys  Create
     * </pre>
     * 
     * @param commands 命令集
     * @param out 输出流
     * @param err 错误输出流
     * @return 执行结果
     */
    private static ExecResult execCommands(@Nonnull final String[] commands, @Nonnull final OutputStream out, @Nonnull final OutputStream err) {
    	Process process = null;
    	PumpStreamHandler sh = new PumpStreamHandler(out, err);
    	try {
            process = Runtime.getRuntime().exec(commands);
        	sh.setProcessInputStream(process.getOutputStream());
        	sh.setProcessOutputStream(process.getInputStream());
        	sh.setProcessErrorStream(process.getErrorStream());
        	process.destroy();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        sh.start();

        int exitValue = Executor.INVALID_EXITVALUE;
        try {
            exitValue = process.waitFor();
        } catch (InterruptedException e) {
            process.destroy();
        } finally {
            // see http://bugs.sun.com/view_bug.do?bug_id=6420270
            // see https://issues.apache.org/jira/browse/EXEC-46
            // Process.waitFor should clear interrupt status when throwing InterruptedException
            // but we have to do that manually
            Thread.interrupted();
        }            

        sh.stop();
        IOException caught = null;
        try {
            process.getInputStream().close();
        } catch (IOException e) {
            caught = e;
        }

        try {
            process.getOutputStream().close();
        } catch (IOException e) {
            caught = e;
        }

        try {
            process.getErrorStream().close();
        } catch (IOException e) {
            caught = e;
        }
        if (caught != null) {
        	throw new RuntimeException(caught);
        }

        ExecResult es =  new ExecResult();
        es.setResult(exitValue);
        es.setCmd(StringArray.join(commands, Files.LINE_SEPARATOR));
		if (out instanceof ByteArrayOutputStream) {
			es.setOutMsg(out.toString());
			es.setErrMsg(err.toString());
		} else {
			es.setOutMsg("See log file for more Infomation.");
			es.setErrMsg("See log file for more Infomation.");
		}
        return es;
    }
    
}
