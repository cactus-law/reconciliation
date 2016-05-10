package com.froad.comon.mail;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.util.Date;
import java.util.Properties;

/**
 * @ClassName: MailAttachmentTool
 * @Description: 发送带附件的邮件
 */
public class MailAttachmentTool {
	
	private static final Log log=LogFactory.getLog(MailAttachmentTool.class);
	
	/**
	 * @Title: sendContainAttachmentMail
	 * @Description: 发送带附件的邮件
	 * @param mailInfo 待发送的邮件的信息
	 * @return boolean
	 * @author chenxiangde
	 */
	public static boolean sendContainAttachmentMail(MailSenderInfo mailInfo) {
		MyAuthenticator authenticator = null;
		Properties pro = mailInfo.getProperties();
		if (mailInfo.isValidate()) {
			// 如果需要身份认证，则创建一个密码验证器
			authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());
		}
		try {
			// 根据邮件会话属性和密码验证器构造一个发送邮件的session
			Session session = Session.getDefaultInstance(pro, authenticator);
			session.setDebug(true);
			MimeMessage message = new MimeMessage(session);
			InternetAddress from = new InternetAddress(mailInfo.getFromAddress());
			message.setFrom(from);
			InternetAddress[] to = InternetAddress.parse(mailInfo.getToAddress());
			message.setRecipients(Message.RecipientType.TO, to);
			message.setSubject(mailInfo.getSubject());
			message.setSentDate(new Date());
			
			// 新建一个MimeMultipart对象用来存放多个BodyPart对象
			MimeMultipart multiPart = new MimeMultipart("mixed");
			message.setContent(multiPart);
			// 设置信件文本内容，新建一个存放信件内容的BodyPart对象
			BodyPart content = new MimeBodyPart();
			// 给BodyPart对象设置内容和格式/编码方式
			content.setContent(mailInfo.getContent(), "text/html; charset=utf-8");
			// 将含有信件内容的BodyPart加入到MimeMultipart对象中
			multiPart.addBodyPart(content);
			
			if (mailInfo.getAttachFileNames() != null && mailInfo.getAttachFileNames().length>0) {
				for(String attachFileName:mailInfo.getAttachFileNames()){
					File file = new File(attachFileName);
					if (file.exists()) {
						// 设置信件的附件
						BodyPart attch = new MimeBodyPart();
						FileDataSource fdataSource = new FileDataSource(attachFileName);
						DataHandler dataHandler = new DataHandler(fdataSource);
						// 提取附件名,可以和原文件名不一致,但最好一样
						String attachmentName = attachFileName.substring(attachFileName.lastIndexOf("/") + 1);
						attch.setFileName(attachmentName);
						attch.setDataHandler(dataHandler);
						multiPart.addBodyPart(attch);
					} else {
						throw new Exception("输入的附件路径不存在!");
					}
				}
				message.setContent(multiPart);
				message.saveChanges();
			}else{
				throw new Exception("输入的附件路径为空!");
			}
			Transport.send(message);
			return true;
		} catch (MessagingException e) {
			log.info("邮件发送失败!具体异常信息:"+e);
			e.printStackTrace();
		} catch (Exception e) {
			log.info("邮件发送失败!",e);
			e.printStackTrace();
		}
		return false;
	}

	public static void main(String[] args) {
		MailSenderInfo mailInfo=new MailSenderInfo();
		mailInfo.setToAddress("chenxiangde@f-road.com.cn");
		mailInfo.setSubject("测试");
		mailInfo.setContent("<a href='http://www.baidu.com'>百度</a>");
		mailInfo.setAttachFileNames(new String[]{"D:/aa/bb.jpg","D:/aa/bb.jpg"});
		MailAttachmentTool.sendContainAttachmentMail(mailInfo);
	}
}