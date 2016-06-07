package com.toobye.common.exec;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.toobye.common.io.Files;

/**
 * <pre> 脚本执行的结果对象.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2013/06/19  huangys  v1.0      Create
 * </pre>
 * 
 */
public final class ExecResult {
	
	private int result;
	private String cmd;
	private String outMsg;
	private String errMsg;
	
	/**
	 * <pre> 获得返回值.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/13  huangys  Create
	 * </pre>
	 * 
	 * @return 返回值
	 */
	@Nonnull
	public int getResult() {
		return result;
	}
	/**
	 * <pre> 设置返回值.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/13  huangys  Create
	 * </pre>
	 * 
	 * @param result 返回值
	 */
	public void setResult(@Nonnull final int result) {
		this.result = result;
	}
	/**
	 * <pre> 获得命令内容.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/13  huangys  Create
	 * </pre>
	 * 
	 * @return 命令内容
	 */
	@Nullable
	public String getCmd() {
		return cmd;
	}
	/**
	 * <pre> 设置命令内容.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/13  huangys  Create
	 * </pre>
	 * 
	 * @param cmd 命令内容
	 */
	public void setCmd(@Nullable final String cmd) {
		this.cmd = cmd;
	}
	/**
	 * <pre> 获得输出日志内容.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/13  huangys  Create
	 * </pre>
	 * 
	 * @return 输出日志内容
	 */
	@Nullable
	public String getOutMsg() {
		return outMsg;
	}
	/**
	 * <pre> 设置输出日志内容.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/13  huangys  Create
	 * </pre>
	 * 
	 * @param outMsg 输出日志内容
	 */
	public void setOutMsg(@Nullable final String outMsg) {
		this.outMsg = outMsg;
	}
	/**
	 * <pre> 获得错误日志内容.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/13  huangys  Create
	 * </pre>
	 * 
	 * @return 错误日志内容
	 */
	@Nullable
	public String getErrMsg() {
		return errMsg;
	}
	/**
	 * <pre> 设置错误日志内容.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/13  huangys  Create
	 * </pre>
	 * 
	 * @param errMsg 错误日志内容
	 */
	public void setErrMsg(@Nullable final String errMsg) {
		this.errMsg = errMsg;
	}
	
	@Override
	public String toString() {
		return "Exit Code: " + this.getResult() + Files.LINE_SEPARATOR
				+ "Command: " + this.getCmd() + Files.LINE_SEPARATOR + Files.LINE_SEPARATOR
				+ "================================== Info ==================================" + Files.LINE_SEPARATOR + this.getOutMsg() + Files.LINE_SEPARATOR + Files.LINE_SEPARATOR
				+ "================================== Error ==================================" + Files.LINE_SEPARATOR + this.getErrMsg();
	}

}