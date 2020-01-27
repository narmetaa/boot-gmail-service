package com.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.serviecs.Gmailserviceimpl;

@RestController
public class Gmailservices {

	@Autowired
	private Gmailserviceimpl service;

	@RequestMapping(value = "/sendmail/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String sendmail(@PathVariable("email") String toemail) {
		System.out.println("sending email subscrption");
		String sendmail = service.sendmail(toemail);

		return sendmail;
	}

}
