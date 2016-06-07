/*
 * Copyright 2016 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2016/05/05.
 * 
 */
package com.toobye.common.net;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import com.toobye.common.lang.Checks;

/**
 * <pre> 邮件发送.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2016/05/05  huangys  v1.0      Create
 * </pre>
 * 
 */
public final class EmailSender {
	
	private HtmlEmail email =  new HtmlEmail();
	private String subject = "";
	private String htmlContent = "";
	private String textContent = "";
	
	/**
	 * <pre> 构造器. </pre>
	 *
	 * @param host 主机
	 * @param port 端口
	 * @param userEmail 用户邮箱
	 * @param userName 用户名
	 * @param userPassword 密码
	 */
	public EmailSender(@Nonnull final String host, @Nonnull final int port, @Nonnull final String userEmail, @Nonnull final String userName, @Nonnull final String userPassword) {
		Checks.nullThrow(host);
		Checks.nullThrow(port);
		Checks.nullThrow(userEmail);
		Checks.nullThrow(userName);
		Checks.nullThrow(userPassword);
		email.setHostName(host);
		email.setSmtpPort(port);
		email.setStartTLSEnabled(true);
		email.setCharset("gb2312");
		email.setAuthentication(userEmail, userPassword);
		try {
			email.setFrom(userEmail, userName);
		} catch (EmailException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <pre> 添加收件人.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/05/05  huangys  Create
	 * </pre>
	 * 
	 * @param toEmail 收件人
	 */
	public void addTo(@Nonnull final String toEmail) {
		Checks.nullThrow(toEmail);
		try {
			email.addTo(toEmail);
		} catch (EmailException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <pre> 添加收件人.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/05/05  huangys  Create
	 * </pre>
	 * 
	 * @param toEmail 收件人
	 * @param name 收件人名称
	 */
	public void addTo(@Nonnull final String toEmail, @Nonnull final String name) {
		try {
			email.addTo(toEmail, name);
		} catch (EmailException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <pre> 设置文本内容.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/05/05  huangys  Create
	 * </pre>
	 * 
	 * @param textContent 文本内容
	 */
	public void setTextContent(@Nullable final String textContent) {
		this.textContent = textContent;
	}
	
	/**
	 * <pre> 设置标题.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/05/05  huangys  Create
	 * </pre>
	 * 
	 * @param subject 标题
	 */
	public void setSubject(@Nullable final String subject) {
		this.subject = subject;
	}
	
	/**
	 * <pre> 设置html内容.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/05/05  huangys  Create
	 * </pre>
	 * 
	 * @param htmlContent html内容
	 */
	public void setHtmlContent(@Nullable final String htmlContent) {
		this.htmlContent = htmlContent;
	}
	
	/**
	 * <pre> 追加Html内容.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/05/05  huangys  Create
	 * </pre>
	 * 
	 * @param appendHtmlContent 追加的html内容
	 */
	public void appendHtmlContent(@Nullable final String appendHtmlContent) {
		this.htmlContent += appendHtmlContent;
	}
	
	/**
	 * <pre> 添加图片.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/05/05  huangys  Create
	 * </pre>
	 * 
	 * @param image 图片
	 * @param href 链接
	 * @param alt 提示
	 */
	public void appendHtmlContentImage(@Nonnull final String image, @Nonnull final String href, @Nonnull final String alt) {
		Checks.nullThrow(image);
		Checks.nullThrow(href);
		Checks.nullThrow(alt);
		URL url = null;
		String cid = "";
		try {
			if (image.toLowerCase().startsWith("http://")) {
				url = new URL(image);
			} else {
				url = new File(image).toURI().toURL();
			}
			cid = email.embed(url, image);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		} catch (EmailException e) {
			throw new RuntimeException(e);
		}
		
		this.htmlContent += "<a href='" + href + "' target='_blank'>"
					+ "<img src='cid:" + cid + "\' alt='" + alt + "' border='0' />"
					+ "</a>";
		
	}
	
	/**
	 * <pre> 发送邮件.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/05/05  huangys  Create
	 * </pre>
	 * 
	 * @return 成功与否
	 */
	public boolean sendMail() {
		try {
			email.setSubject(subject);
			if (htmlContent != null && (!htmlContent.equals(""))) {
				email.setHtmlMsg("<html>" + htmlContent + "</html>");
			}
			if (textContent != null && (!textContent.equals(""))) {
				email.setTextMsg(textContent);
			}
			email.send();
			return true;
		} catch (EmailException e) {
			throw new RuntimeException(e);
		}
	}
	
}
