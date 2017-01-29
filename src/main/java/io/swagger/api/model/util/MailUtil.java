	package io.swagger.api.model.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtil {
	
	private static final String USUARIO = "COLOQUE SEU USUARIO";
	private static final String SENHA = "COLOQUE SUA SENHA";

	public static void enviarEmail(String msg, String mailDestino) {

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

//		É necessário autorizar o envio por serviços inseguros, no caso do gmail.
//		link: https://www.google.com/settings/security/lesssecureapps
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(USUARIO, SENHA);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(USUARIO));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailDestino));
			message.setSubject("Serviço de cadastro");
			message.setText(msg);

			Transport.send(message);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

}
