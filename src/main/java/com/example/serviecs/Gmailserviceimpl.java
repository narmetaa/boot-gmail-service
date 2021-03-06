package com.example.serviecs;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;
import org.validation.Validation;

import com.example.models.DemoJson;
import com.google.gson.Gson;

@Service
public class Gmailserviceimpl {

	public String sendmail(String toemail) {

		System.out.println("hit the gmail services..");

		boolean validMail = Validation.isValidMail(toemail);

		if (validMail != true) {
			DemoJson json = json("incorrect mail id", "003");
			Gson gson = new Gson();
			String result = gson.toJson(json);
			System.out.println(result);
			return result;

		}

		final String username = "username@gmail.com";
		final String password = "123456799";
		

		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true"); // TLS

		Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("narmetaa@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toemail));
			message.setSubject("The subscrption letter from SMS Hub  ");
			message.setText("Thankyou for subscribe our email channel let you know our latest upadates");

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			e.printStackTrace();
		}
		System.out.println("email method has completed succsfully...");

		DemoJson json = json("succss", "001");
		Gson gson = new Gson();
		String result = gson.toJson(json);
		System.out.println(result);

		return result;

	}

	public static DemoJson json(String msg, String code) {

		DemoJson json = new DemoJson();
		json.setId(code);
		json.setMeassage(msg);
		json.setType("subscription");

		return json;

	}

}
