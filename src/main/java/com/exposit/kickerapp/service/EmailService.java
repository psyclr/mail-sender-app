package com.exposit.kickerapp.service;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.exposit.kickerapp.request.EmailRequest;
import com.exposit.kickerapp.sender.impl.MailGunSender;
import com.exposit.kickerapp.sender.impl.SendGridSender;
import com.sendgrid.Response;

@Service("emailService")
public class EmailService
{
	private Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

	@Resource(name = "mailGunSender")
	private MailGunSender mailGunSender;

	@Resource(name = "sendGridSender")
	private SendGridSender sendGridSender;

	public Response sendMail(EmailRequest emailRequest)
	{
		int responseCodeSendGrid = sendGridSender.send(emailRequest);
		if (responseCodeSendGrid != 202)
		{
			LOGGER.info("Sending email by SendGrid failed, try to send by MailGun");
			int responseCodeMailGun = mailGunSender.send(emailRequest);
			return new Response(responseCodeMailGun, "successful", null);
		}
		else
		{
			LOGGER.info("Successfully send by SendGrid");
			return new Response(responseCodeSendGrid, "", null);
		}
	}
}
