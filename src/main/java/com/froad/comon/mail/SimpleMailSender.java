package com.froad.comon.mail;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Date;
import java.util.Properties;

/**
 * @ClassName: SimpleMailSender
 * @Description: 简单邮件（不带附件的邮件）发送器
 */
public class SimpleMailSender {
	
	private static final Log log=LogFactory.getLog(SimpleMailSender.class);
	
	/**
	 * @Title: sendTextMail
	 * @Description: 以文本格式发送邮件
	 * @param mailInfo 待发送的邮件的信息
	 * @return boolean
	 * @author chenxiangde
	 */
	public static boolean sendTextMail(MailSenderInfo mailInfo) {
		// 判断是否需要身份认证
		MyAuthenticator authenticator = null;
		Properties pro = mailInfo.getProperties();
		if (mailInfo.isValidate()) {
			// 如果需要身份认证，则创建一个密码验证器
			authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());
		}
		try {
			// 根据邮件会话属性和密码验证器构造一个发送邮件的session
			Session sendMailSession = Session.getDefaultInstance(pro, authenticator);
			// 根据session创建一个邮件消息
			Message mailMessage = new MimeMessage(sendMailSession);
			// 创建邮件发送者地址
			Address from = new InternetAddress(mailInfo.getFromAddress());
			// 设置邮件消息的发送者
			mailMessage.setFrom(from);
			// 创建邮件的接收者地址，并设置到邮件消息中
			InternetAddress[] to = InternetAddress.parse(mailInfo.getToAddress());
			// Message.RecipientType.TO属性表示接收者的类型为TO
			mailMessage.setRecipients(Message.RecipientType.TO, to);
			// 设置邮件消息的主题
			mailMessage.setSubject(mailInfo.getSubject());
			// 设置邮件消息发送的时间
			mailMessage.setSentDate(new Date());
			// 设置邮件消息的主要内容
			String mailContent = mailInfo.getContent();
			mailMessage.setText(mailContent);
			// 发送邮件
			Transport.send(mailMessage);
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
	
	/**
	 * @Title: sendHtmlMail
	 * @Description: 以HTML格式发送邮件
	 * @param mailInfo 待发送的邮件信息
	 * @return boolean
	 * @author chenxiangde
	 */
	public static boolean sendHtmlMail(MailSenderInfo mailInfo) {
		// 判断是否需要身份认证
		MyAuthenticator authenticator = null;
		Properties pro = mailInfo.getProperties();
		// 如果需要身份认证，则创建一个密码验证器
		if (mailInfo.isValidate()) {
			authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());
		}
		try {
			// 根据邮件会话属性和密码验证器构造一个发送邮件的session
			Session sendMailSession = Session.getDefaultInstance(pro, authenticator);
			// 根据session创建一个邮件消息
			Message mailMessage = new MimeMessage(sendMailSession);
			// 创建邮件发送者地址
			Address from = new InternetAddress(mailInfo.getFromAddress());
			// 设置邮件消息的发送者
			mailMessage.setFrom(from);
			// 创建邮件的接收者地址，并设置到邮件消息中
			InternetAddress[] to = InternetAddress.parse(mailInfo.getToAddress());
			// Message.RecipientType.TO属性表示接收者的类型为TO
			mailMessage.setRecipients(Message.RecipientType.TO, to);
			// 设置邮件消息的主题
			mailMessage.setSubject(mailInfo.getSubject());
			// 设置邮件消息发送的时间
			mailMessage.setSentDate(new Date());
			// MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
			Multipart mainPart = new MimeMultipart();
			// 创建一个包含HTML内容的MimeBodyPart
			BodyPart html = new MimeBodyPart();
			// 设置HTML内容
			html.setContent(mailInfo.getContent(), "text/html; charset=utf-8");
			mainPart.addBodyPart(html);
			// 将MiniMultipart对象设置为邮件内容
			mailMessage.setContent(mainPart);
			// 发送邮件
			Transport.send(mailMessage);
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
		SimpleMailSender.sendHtmlMail(mailInfo);
	}
}