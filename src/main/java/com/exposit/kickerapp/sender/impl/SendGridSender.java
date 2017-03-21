package com.exposit.kickerapp.sender.impl;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.exposit.kickerapp.request.EmailRequest;
import com.exposit.kickerapp.sender.Sender;
import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.SendGrid;

import static com.exposit.kickerapp.Constant.CONTENT_TYPE_TEXT_PLAIN;
import static com.exposit.kickerapp.Constant.SENDGRID_ENDPOINT;

@Service("sendGridSender")
public class SendGridSender implements Sender
{
	private Logger LOGGER = LoggerFactory.getLogger(SendGridSender.class);

	@Value("${sengrid.api-key}")
	private String apiKey;

	@Override
	public int send(EmailRequest emailRequest)
	{
		LOGGER.info("Start sending email by SendGrid API");
		SendGrid sg = new SendGrid(apiKey);

		Mail mail = new Mail(
				new Email(emailRequest.getFrom()),
				emailRequest.getSubject(),
				new Email(emailRequest.getTo()),
				new Content(CONTENT_TYPE_TEXT_PLAIN, emailRequest.getContent()));

		Request request = new Request();
		request.method = Method.POST;
		request.endpoint = SENDGRID_ENDPOINT;
		try
		{
			request.body = mail.build();
			return sg.api(request).statusCode;
		}
		catch (IOException e)
		{
			LOGGER.error(e.getMessage(), e);
			return 400;
		}

	}
}
