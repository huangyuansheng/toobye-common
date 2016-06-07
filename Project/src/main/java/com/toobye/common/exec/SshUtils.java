/*
 * Copyright 2016 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2016/05/31.
 * 
 */
package com.toobye.common.exec;

import javax.annotation.Nonnull;

import com.toobye.common.io.Files;
import com.toobye.common.net.ConnInfo;

/**
 * <pre> Ssh工具类.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2016/05/31  huangys  v1.0      Create
 * </pre>
 * 
 */
public final class SshUtils {
	
	private SshUtils() { }
	
	/**
	 * <pre> 文件是否存在.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/05/31  huangys  Create
	 * </pre>
	 * 
	 * @param info 连接信息
	 * @param fileName 文件名
	 * @return 执行信息 null为存在，否则不存在
	 */
	public static String exists(@Nonnull final ConnInfo info, @Nonnull final String fileName) {
		String cmd = "file " + fileName;
		ExecResult er = SshExecutor.execCommand(info, cmd);
		if (er.getResult() != 0) {
			return "Command execution error(Command: " + cmd + ")."
								+ Files.LINE_SEPARATOR + "Out: " + er.getOutMsg()
								+ Files.LINE_SEPARATOR + "Err: " + er.getErrMsg();
		} else if (er.getOutMsg().contains("No such file or directory")) {
			return "Sftp file doesnot exists.";
		} else {
			if (er.getOutMsg().contains(": directory")) {
				return "Sftp file is directory.";
			} else {
				return null;
			}
		}
	}

}
