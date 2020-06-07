package com.id.perpus.common;


import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;


@Component
public class Mail {

	/*@Autowired
	private JavaMailSender javaMailSender;*/

	public void send(String to, String subject, String body) throws Exception {
		/*
		 * MimeMessage mail = javaMailSender.createMimeMessage();
		 * MimeMessageHelper helper = new MimeMessageHelper(mail, true);
		 * helper.setTo(to); helper.setFrom(from); helper.setSubject(subject);
		 * helper.setText(body,true); javaMailSender.send(mail);
		 */

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "587");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.starttls.enable", "true");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(Constanta.EMAIL_USERNAME, Constanta.EMAIL_PASSWORD);
			}
		});

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(Constanta.EMAIL_FROM));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject(subject);
//			message.setText(body, "text/html; charset=utf-8");
			message.setContent(body, "text/html; charset=utf-8");
			//message.setText(body,"text/html");
			// body,"text/html"
			
			// Send message
			Transport.send(message);
			System.out.println("message sent successfully....");

		} catch (MessagingException e) {
			throw e;
		}
	}
	public static void main(String[] args){
		Mail test = new Mail();
		try {
			test.send("permana.yuda.jtk07@gmail.com", "test","test<br> test");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
