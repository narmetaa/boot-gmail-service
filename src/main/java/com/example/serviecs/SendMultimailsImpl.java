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
public class SendMultimailsImpl {

	public DemoJson sendMultipleMails(String email, String subject, String body) {

		System.out.println("executing multple mails service...");
		String validMail = String.valueOf(Validation.isValidMail(email));
		
		String[] split = validMail.split(",");
		
		int count=0;
		
		for(int i=0;i<=validMail.length();i++) {
			
			count=count++;
		}
		
		System.out.println(count);

		/*
		 * if (validMail != true) {
		 * 
		 * DemoJson json = Gmailserviceimpl.json("invalid emails", "003"); Gson gson =
		 * new Gson(); String result = gson.toJson(json); System.out.println(result);
		 * 
		 * return json; }
		 */

		if (subject.isEmpty() || subject == null || body.isEmpty() || body == null) {
			DemoJson json = Gmailserviceimpl.json("data should not be empty", "006");
			Gson gson = new Gson();
			String result = gson.toJson(json);
			System.out.println(result);

			return json;

		}

		final String username = "narmetaa@gmail.com";
		final String password = "8686112774";

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
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
			message.setSubject(subject);
			message.setText(body);

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {

			DemoJson json = json("No Email Found", "005");
			Gson gson = new Gson();
			String result = gson.toJson(json);
			System.out.println(result);
			return json;

		}
		System.out.println("email method has completed succsfully...");

		DemoJson json = json("succss", "001");
		Gson gson = new Gson();
		String result = gson.toJson(json);
		System.out.println(result);

		return json;

	}

	public static DemoJson json(String msg, String code) {

		DemoJson json = new DemoJson();
		json.setId(code);
		json.setMeassage(msg);
		json.setType("subscription");

		return json;

	}

}
