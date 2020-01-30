package com.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.models.DemoJson;
import com.example.serviecs.SendMultimailsImpl;

@RestController
public class SendMultimails {

	@Autowired
	private SendMultimailsImpl multipleservice;

	@RequestMapping(value = "sendmails/{email}/{sub}/{body}",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},
			method = RequestMethod.POST)
	
	public DemoJson sendMultipleMails(@PathVariable("email") String email, @PathVariable("sub") String subject,
			@PathVariable("body") String body) {
		System.out.println("executing multiple mail servicess,,");
		System.out.println(email + subject + body);

		 DemoJson result = multipleservice.sendMultipleMails(email, subject, body);
		
		System.out.println("service end..");
		return result;

	}

}
