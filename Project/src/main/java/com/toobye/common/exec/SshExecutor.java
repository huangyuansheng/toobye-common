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
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.sshd.ClientChannel;
import org.apache.sshd.ClientSession;
import org.apache.sshd.SshClient;

import com.toobye.common.base.Exceptions;
import com.toobye.common.concurrent.TimeSlice;
import com.toobye.common.net.ConnInfo;
import com.toobye.common.string.StringArray;

/**
 * <pre> 远程SSH执行器.
 * 针对 Too many open files错误：
 *     vi /etc/security/limits.conf
 *     增加大括号中内容
 *     {
 *        $username hard nofile 65535
 *        $username soft nofile 65535
 *     }
 *     查看方式：ulimit -n
 *     
 * Modification History:
 * Date        Author   Version   Action
 * 2014/02/28  huangys  v1.0      Create
 * </pre>
 * 
 */
public final class SshExecutor {
	
	private SshExecutor() { }
	
	/**
	 * <pre> 执行脚本文件.
	 * 依据脚本后缀生成可执行脚本命令。
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/02/28  huangys  Create
	 * </pre>
	 * 
	 * @param connInfo 连接信息
	 * @param scriptFile 脚本名称
	 * @return 执行结果
	 * @see LocalExecutor#getScriptFileCommandUsingSuffix(String)
	 */
	@Nonnull
	public static ExecResult execScriptFile(@Nonnull final ConnInfo connInfo, @Nonnull final String scriptFile) {
		return execScriptFile(connInfo, scriptFile, null, (TimeSlice) null);
	}
	
	/**
	 * <pre> 执行脚本文件.
	 * 依据脚本后缀生成可执行脚本命令。
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/02/28  huangys  Create
	 * </pre>
	 * 
	 * @param connInfo 连接信息
	 * @param scriptFile 脚本名称
	 * @param logFile 输出日志文件
	 * @return 执行结果
	 * @see LocalExecutor#getScriptFileCommandUsingSuffix(String)
	 */
	@Nonnull
	public static ExecResult execScriptFile(@Nonnull final ConnInfo connInfo, @Nonnull final String scriptFile, @Nonnull final String logFile) {
		return execScriptFile(connInfo, scriptFile, null, logFile, (TimeSlice) null);
	}
	
	/**
	 * <pre> 执行脚本文件.
	 * 依据脚本后缀生成可执行脚本命令。
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/02/28  huangys  Create
	 * </pre>
	 * 
	 * @param connInfo 连接信息
	 * @param scriptFile 脚本名称
	 * @param params 参数集合
	 * @return 执行结果
	 * @see LocalExecutor#getScriptFileCommandUsingSuffix(String)
	 */
	@Nonnull
	public static ExecResult execScriptFile(@Nonnull final ConnInfo connInfo, @Nonnull final String scriptFile, @Nullable final String[] params) {
		return execScriptFile(connInfo, scriptFile, params, (TimeSlice) null);
	}
	
	/**
	 * <pre> 执行脚本文件.
	 * 依据脚本后缀生成可执行脚本命令。
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/02/28  huangys  Create
	 * </pre>
	 * 
	 * @param connInfo 连接信息
	 * @param scriptFile 脚本名称
	 * @param params 参数集合
	 * @param logFile 输出日志文件
	 * @return 执行结果
	 * @see LocalExecutor#getScriptFileCommandUsingSuffix(String)
	 */
	@Nonnull
	public static ExecResult execScriptFile(@Nonnull final ConnInfo connInfo, @Nonnull final String scriptFile, @Nullable final String[] params, @Nonnull final String logFile) {
		return execScriptFile(connInfo, scriptFile, params, logFile, (TimeSlice) null);
	}
	
	/**
	 * <pre> 执行脚本文件.
	 * 依据脚本后缀生成可执行脚本命令。
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/03/01  huangys  Create
	 * </pre>
	 * 
	 * @param connInfo 连接信息
	 * @param scriptFile 脚本名称
	 * @param params 参数集合
	 * @param timeout 超时设置
	 * @return 执行结果
	 * @see LocalExecutor#getScriptFileCommandUsingSuffix(String)
	 */
	@Nonnull
	public static ExecResult execScriptFile(@Nonnull final ConnInfo connInfo, @Nonnull final String scriptFile, @Nullable final String[] params, @Nullable final TimeSlice timeout) {
		return execCommand(connInfo, LocalExecutor.getScriptFileCommandUsingSuffix(scriptFile), params, timeout);
	}
	
	/**
	 * <pre> 执行脚本文件.
	 * 依据脚本后缀生成可执行脚本命令。
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/03/01  huangys  Create
	 * </pre>
	 * 
	 * @param connInfo 连接信息
	 * @param scriptFile 脚本名称
	 * @param params 参数集合
	 * @param logFile 输出日志文件
	 * @param timeout 超时设置
	 * @return 执行结果
	 * @see LocalExecutor#getScriptFileCommandUsingSuffix(String)
	 */
	@Nonnull
	public static ExecResult execScriptFile(@Nonnull final ConnInfo connInfo, @Nonnull final String scriptFile, @Nullable final String[] params, @Nonnull final String logFile, @Nullable final TimeSlice timeout) {
		return execCommand(connInfo, LocalExecutor.getScriptFileCommandUsingSuffix(scriptFile), params, logFile, timeout);
	}
	
	/**
	 * <pre> 执行命令行.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/02/28  huangys  Create
	 * </pre>
	 * 
	 * @param connInfo 连接信息
	 * @param cmd 命令内容
	 * @return 执行结果
	 */
	@Nonnull
	public static ExecResult execCommand(@Nonnull final ConnInfo connInfo, @Nonnull final String cmd) {
		return execCommand(connInfo, cmd, null, (TimeSlice) null);
	}
	
	/**
	 * <pre> 执行命令行.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/02/28  huangys  Create
	 * </pre>
	 * 
	 * @param connInfo 连接信息
	 * @param cmd 命令内容
	 * @param logFile 输出日志文件
	 * @return 执行结果
	 */
	@Nonnull
	public static ExecResult execCommand(@Nonnull final ConnInfo connInfo, @Nonnull final String cmd, @Nonnull final String logFile) {
		return execCommand(connInfo, cmd, null, logFile, (TimeSlice) null);
	}
	
	/**
	 * <pre> 执行命令行.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/02/28  huangys  Create
	 * </pre>
	 * 
	 * @param connInfo 连接信息
	 * @param cmd 命令内容
	 * @param params 参数集合
	 * @return 执行结果
	 */
	@Nonnull
	public static ExecResult execCommand(@Nonnull final ConnInfo connInfo, @Nonnull final String cmd, @Nullable final String[] params) {
		return execCommand(connInfo, cmd, params, (TimeSlice) null);
	}
	
	/**
	 * <pre> 执行命令行.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/02/28  huangys  Create
	 * </pre>
	 * 
	 * @param connInfo 连接信息
	 * @param cmd 命令内容
	 * @param params 参数集合
	 * @param logFile 输出日志文件
	 * @return 执行结果
	 */
	@Nonnull
	public static ExecResult execCommand(@Nonnull final ConnInfo connInfo, @Nonnull final String cmd, @Nullable final String[] params, @Nonnull final String logFile) {
		return execCommand(connInfo, cmd, params, logFile, (TimeSlice) null);
	}
	
	/**
	 * <pre> 执行命令行.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/02/28  huangys  Create
	 * </pre>
	 * 
	 * @param connInfo 连接信息
	 * @param cmd 命令内容
	 * @param params 参数集合
	 * @param timeout 超时设置
	 * @return 执行结果
	 */
	@Nonnull
	public static ExecResult execCommand(@Nonnull final ConnInfo connInfo, @Nonnull final String cmd, @Nullable final String[] params, @Nullable final TimeSlice timeout) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             ByteArrayOutputStream err = new ByteArrayOutputStream();) {
    		return execCommand(connInfo, cmd, params, out, err, timeout);
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
	 * @param connInfo 连接信息
	 * @param cmd 命令内容
	 * @param params 参数集合
	 * @param logFile 输出日志文件
	 * @param timeout 超时设置
	 * @return 执行结果
	 */
	@Nonnull
	public static ExecResult execCommand(@Nonnull final ConnInfo connInfo, @Nonnull final String cmd, @Nullable final String[] params, @Nonnull final String logFile, @Nullable final TimeSlice timeout) {
		try (FileOutputStream fout = new FileOutputStream(logFile);) {
			return execCommand(connInfo, cmd, params, fout, fout, timeout);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <pre> 执行命令行.
	 * CLOSED: The underlying SSH-2 channel, however not necessarily the whole connection, has been closed.
	 * EOF: EOF on has been reached, no more _new_ stdout or stderr data will arrive from the remote server.
	 * EXIT_SIGNAL: The exit signal of the remote process is available.
	 * EXIT_STATUS：The exit status of the remote process is available.
	 * STDERR_DATA：There is stderr data available that is ready to be consumed.
	 * STDOUT_DATA：There is stdout data available that is ready to be consumed.
	 * TIMEOUT: A timeout has occurred, none of your requested conditions is fulfilled.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/02/28  huangys  Create
	 * </pre>
	 * 
	 * @param connInfo 连接信息
	 * @param cmd 命令内容
	 * @param params 参数集合
	 * @param out 输出流
	 * @param err 错误输出流
	 * @param timeout 超时设置
	 * @return 执行结果
	 */
	@SuppressWarnings("deprecation")
	@Nonnull
	public static ExecResult execCommand(@Nonnull final ConnInfo connInfo, @Nonnull final String cmd, @Nullable final String[] params, @Nonnull final OutputStream out, @Nonnull final OutputStream err, @Nullable final TimeSlice timeout) {
		SshClient client = null;
    	ClientChannel channel = null;
    	try {
    		try {
        		// 创建连接
            	client = SshClient.setUpDefaultClient();
                client.start();
                // ClientSession session = client.connect(host, port).await().getSession();
                // session.authPassword(user, password).await().isSuccess();
                ClientSession session = client.connect(connInfo.getUser(), connInfo.getHost(), connInfo.getPort()).await().getSession();
                session.addPasswordIdentity(connInfo.getPassword());
                session.authPassword(connInfo.getUser(), connInfo.getPassword()).await().isSuccess();
                channel = session.createChannel(ClientChannel.CHANNEL_SHELL);

                // 设置输入流
                PipedOutputStream inStream = new SshStream(new ByteArrayOutputStream());
                channel.setIn(new PipedInputStream(inStream));
                // 设置输出流
                channel.setOut(out);
                channel.setErr(err);
                
                // 打开链接
                channel.open().await();

                // 执行命令
                if (params == null || params.length == 0) {
                	inStream.write((cmd + "\n").getBytes());
				} else {
					inStream.write((cmd + " " + StringArray.join(params, " ") + "\n").getBytes());
				}
                inStream.flush();
                // 退出连接
                inStream.write(("exit" + "\n").getBytes());
                inStream.flush();
                // 等待退出
                channel.waitFor(ClientChannel.CLOSED, timeout == null ? 0 : timeout.toSeconds());
			} catch (Exception e) {
				// throw new RuntimeException(e);
				try {
					err.write(Exceptions.getStackTrace(e).getBytes());
				} catch (Exception e2) {
					// Do Nothing
					// throw new RuntimeException(e);
				}
			}
    		ExecResult ret = new ExecResult();
    		ret.setCmd(cmd);
    		if (channel != null && channel.getExitStatus() != null) {
    			ret.setResult(channel.getExitStatus());
			} else {
				ret.setResult(-1);
			}
    		
    		if (out instanceof ByteArrayOutputStream) {
    			ret.setOutMsg(out.toString());
    			ret.setErrMsg(err.toString());
    		} else {
    			ret.setOutMsg("See log file for more Infomation.");
    			ret.setErrMsg("See log file for more Infomation.");
    		}
    		return ret;
		} finally {
			if (channel != null) {
				try { channel.close(false); } catch (Exception e2) { }
			}
			if (client != null) {
				try { client.stop(); } catch (Exception e2) { }
			}
		}
	}
	
	/**
	 * <pre> 执行脚本文件，续写日志文件.
	 * 依据脚本后缀生成可执行脚本命令。
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/02/28  huangys  Create
	 * </pre>
	 * 
	 * @param connInfo 连接信息
	 * @param scriptFile 脚本名称
	 * @param logFile 输出日志文件
	 * @return 执行结果
	 * @see LocalExecutor#getScriptFileCommandUsingSuffix(String)
	 */
	@Nonnull
	public static ExecResult execScriptFileAppend(@Nonnull final ConnInfo connInfo, @Nonnull final String scriptFile, @Nonnull final String logFile) {
		return execScriptFileAppend(connInfo, scriptFile, null, logFile, (TimeSlice) null);
	}
	
	/**
	 * <pre> 执行脚本文件，续写日志文件.
	 * 依据脚本后缀生成可执行脚本命令。
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/02/28  huangys  Create
	 * </pre>
	 * 
	 * @param connInfo 连接信息
	 * @param scriptFile 脚本名称
	 * @param params 参数集合
	 * @param logFile 输出日志文件
	 * @return 执行结果
	 * @see LocalExecutor#getScriptFileCommandUsingSuffix(String)
	 */
	@Nonnull
	public static ExecResult execScriptFileAppend(@Nonnull final ConnInfo connInfo, @Nonnull final String scriptFile, @Nullable final String[] params, @Nonnull final String logFile) {
		return execScriptFileAppend(connInfo, scriptFile, params, logFile, (TimeSlice) null);
	}
	
	/**
	 * <pre> 执行脚本文件，续写日志文件.
	 * 依据脚本后缀生成可执行脚本命令。
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/03/01  huangys  Create
	 * </pre>
	 * 
	 * @param connInfo 连接信息
	 * @param scriptFile 脚本名称
	 * @param params 参数集合
	 * @param logFile 输出日志文件
	 * @param timeout 超时设置
	 * @return 执行结果
	 * @see LocalExecutor#getScriptFileCommandUsingSuffix(String)
	 */
	@Nonnull
	public static ExecResult execScriptFileAppend(@Nonnull final ConnInfo connInfo, @Nonnull final String scriptFile, @Nullable final String[] params, @Nonnull final String logFile, @Nullable final TimeSlice timeout) {
		return execCommandAppend(connInfo, LocalExecutor.getScriptFileCommandUsingSuffix(scriptFile), params, logFile, timeout);
	}
	
	/**
	 * <pre> 执行命令行，续写日志文件.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/02/28  huangys  Create
	 * </pre>
	 * 
	 * @param connInfo 连接信息
	 * @param cmd 命令内容
	 * @param logFile 输出日志文件
	 * @return 执行结果
	 */
	@Nonnull
	public static ExecResult execCommandAppend(@Nonnull final ConnInfo connInfo, @Nonnull final String cmd, @Nonnull final String logFile) {
		return execCommandAppend(connInfo, cmd, null, logFile, (TimeSlice) null);
	}
	
	/**
	 * <pre> 执行命令行，续写日志文件.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/02/28  huangys  Create
	 * </pre>
	 * 
	 * @param connInfo 连接信息
	 * @param cmd 命令内容
	 * @param params 参数集合
	 * @param logFile 输出日志文件
	 * @return 执行结果
	 */
	@Nonnull
	public static ExecResult execCommandAppend(@Nonnull final ConnInfo connInfo, @Nonnull final String cmd, @Nullable final String[] params, @Nonnull final String logFile) {
		return execCommandAppend(connInfo, cmd, params, logFile, (TimeSlice) null);
	}
	
	/**
	 * <pre> 执行命令行，续写日志文件.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/02/28  huangys  Create
	 * </pre>
	 * 
	 * @param connInfo 连接信息
	 * @param cmd 命令内容
	 * @param params 参数集合
	 * @param logFile 输出日志文件
	 * @param timeout 超时设置
	 * @return 执行结果
	 */
	@Nonnull
	public static ExecResult execCommandAppend(@Nonnull final ConnInfo connInfo, @Nonnull final String cmd, @Nullable final String[] params, @Nonnull final String logFile, @Nullable final TimeSlice timeout) {
		try (FileOutputStream fout = new FileOutputStream(logFile, true);) {
			return execCommand(connInfo, cmd, params, fout, fout, timeout);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
