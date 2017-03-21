package com.exposit.kickerapp.rest;

import javax.annotation.Resource;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.exposit.kickerapp.request.EmailRequest;
import com.exposit.kickerapp.service.EmailService;

@RestController
public class EmailController
{

	@Resource(name = "emailService")
	private EmailService emailService;

	@RequestMapping(value = "/send", method = RequestMethod.POST)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUser(@RequestBody EmailRequest emailRequest)
	{
		com.sendgrid.Response response = emailService.sendMail(emailRequest);
		return Response.status(response.statusCode).build();
	}
}

