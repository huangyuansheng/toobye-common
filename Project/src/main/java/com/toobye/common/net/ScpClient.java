/*
 * Copyright 2014 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2014/04/23.
 * 
 */
package com.toobye.common.net;

import javax.annotation.Nonnull;

import org.apache.sshd.ClientSession;
import org.apache.sshd.SshClient;
import org.apache.sshd.client.ScpClient.Option;
import org.apache.sshd.client.scp.DefaultScpClient;

/**
 * <pre> SFTP客户端.
 * 
 * sshd中的Option.PreserveAttributes
 * 	就是scp中参数-p，Preserves modification times, access times, and modes from the original file.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2014/04/23  huangys  v1.0      Create
 * </pre>
 * 
 */
public final class ScpClient implements AutoCloseable {
	
	private org.apache.sshd.client.ScpClient scpClient;
	private SshClient sshClient;

	/**
	 * <pre> 构造器. </pre>
	 *
	 * @param connInfo 连接信息
	 */
	public ScpClient(@Nonnull final ConnInfo connInfo) {
		try {
    		// 创建连接
			sshClient = SshClient.setUpDefaultClient();
			sshClient.start();
            ClientSession session = sshClient.connect(connInfo.getUser(), connInfo.getHost(), connInfo.getPort()).await().getSession();
            session.addPasswordIdentity(connInfo.getPassword());
            session.auth().await().isSuccess();
            scpClient = new DefaultScpClient(session);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <pre> 上传本地文件至远程.
	 * 
	 * 当本地为文件，远程为目录时，以uploadToDiretory方式智能实现
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/09/28  huangys  Create
	 * </pre>
	 * 
	 * @param local 本地文件
	 * @param remote 远程文件
	 */
	public void upload(@Nonnull final String local, @Nonnull final String remote) {
		upload(local, remote, false);
	}
	
	/**
	 * <pre> 上传本地文件至远程.
	 * 
	 * 当本地为文件，远程为目录时，以uploadToDiretory方式智能实现
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/09/28  huangys  Create
	 * </pre>
	 * 
	 * @param local 本地文件
	 * @param remote 远程文件
	 * @param recursive 是否遍历子文件夹
	 */
	public void upload(@Nonnull final String local, @Nonnull final String remote, @Nonnull final boolean recursive) {
		try {
			if (recursive) {
				scpClient.upload(local, remote, Option.Recursive);
			} else {
				scpClient.upload(local, remote);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <pre> 上传本地文件至远程.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/09/28  huangys  Create
	 * </pre>
	 * 
	 * @param local 本地文件
	 * @param remote 远程目录
	 */
	public void uploadToDiretory(@Nonnull final String local, @Nonnull final String remote) {
		uploadToDiretory(local, remote, false);
	}
	
	/**
	 * <pre> 上传本地文件至远程.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/09/28  huangys  Create
	 * </pre>
	 * 
	 * @param local 本地文件
	 * @param remote 远程目录
	 * @param recursive 是否遍历子文件夹
	 */
	public void uploadToDiretory(@Nonnull final String local, @Nonnull final String remote, @Nonnull final boolean recursive) {
		uploadToDiretory(new String[] { local}, remote, recursive);
	}
	
	/**
	 * <pre> 上传本地文件至远程.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/09/28  huangys  Create
	 * </pre>
	 * 
	 * @param local 本地文件清单
	 * @param remote 远程目录
	 */
	public void uploadToDiretory(@Nonnull final String[] local, @Nonnull final String remote) {
		uploadToDiretory(local, remote, false);
	}
	
	/**
	 * <pre> 上传本地文件至远程.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/09/28  huangys  Create
	 * </pre>
	 * 
	 * @param local 本地文件清单
	 * @param remote 远程目录
	 * @param recursive 是否遍历子文件夹
	 */
	public void uploadToDiretory(@Nonnull final String[] local, @Nonnull final String remote, @Nonnull final boolean recursive) {
		try {
			if (recursive) {
				scpClient.upload(local, remote, Option.Recursive);
			} else {
				scpClient.upload(local, remote);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <pre> 下载远程文件至本地.
	 * 
	 * 当远程为文件，本地为目录时，以downloadToDiretory方式智能实现
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/09/28  huangys  Create
	 * </pre>
	 * 
	 * @param remote 远程文件
	 * @param local 本地文件
	 */
	public void download(@Nonnull final String remote, @Nonnull final String local) {
		download(local, remote, false);
	}
	
	/**
	 * <pre> 下载远程文件至本地.
	 * 
	 * 当远程为文件，本地为目录时，以downloadToDiretory方式智能实现
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/09/28  huangys  Create
	 * </pre>
	 * 
	 * @param remote 远程文件
	 * @param local 本地文件
	 * @param recursive 是否遍历子文件夹
	 */
	public void download(@Nonnull final String remote, @Nonnull final String local, @Nonnull final boolean recursive) {
		try {
			if (recursive) {
				scpClient.download(local, remote, Option.Recursive);
			} else {
				scpClient.download(local, remote);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <pre> 下载远程文件至本地.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/09/28  huangys  Create
	 * </pre>
	 * 
	 * @param remote 远程文件
	 * @param local 本地目录
	 */
	public void downloadToDiretory(@Nonnull final String remote, @Nonnull final String local) {
		downloadToDiretory(local, remote, false);
	}
	
	/**
	 * <pre> 下载远程文件至本地.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/09/28  huangys  Create
	 * </pre>
	 * 
	 * @param remote 远程文件
	 * @param local 本地目录
	 * @param recursive 是否遍历子文件夹
	 */
	public void downloadToDiretory(@Nonnull final String remote, @Nonnull final String local, @Nonnull final boolean recursive) {
		downloadToDiretory(new String[] { local}, remote, recursive);
	}
	
	/**
	 * <pre> 下载远程文件至本地.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/09/28  huangys  Create
	 * </pre>
	 * 
	 * @param remote 远程文件清单
	 * @param local 本地目录
	 */
	public void downloadToDiretory(@Nonnull final String[] local, @Nonnull final String remote) {
		downloadToDiretory(local, remote, false);
	}
	
	/**
	 * <pre> 下载远程文件至本地.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/09/28  huangys  Create
	 * </pre>
	 * 
	 * @param remote 远程文件清单
	 * @param local 本地目录
	 * @param recursive 是否遍历子文件夹
	 */
	public void downloadToDiretory(@Nonnull final String[] local, @Nonnull final String remote, @Nonnull final boolean recursive) {
		try {
			if (recursive) {
				scpClient.download(local, remote, Option.Recursive);
			} else {
				scpClient.download(local, remote);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void close() {
		if (sshClient != null) {
			try { sshClient.stop(); } catch (Exception e2) { }
		}
	}
	
}
