package com.exposit.kickerapp.sender.impl;

import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.exposit.kickerapp.request.EmailRequest;
import com.exposit.kickerapp.sender.Sender;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import static com.exposit.kickerapp.Constant.MAILGUN_BASE_URL;
import static com.exposit.kickerapp.Constant.MAILGUN_CONTENT;
import static com.exposit.kickerapp.Constant.MAILGUN_FROM;
import static com.exposit.kickerapp.Constant.MAILGUN_MESSAGES;
import static com.exposit.kickerapp.Constant.MAILGUN_POSTFIX;
import static com.exposit.kickerapp.Constant.MAILGUN_PREFIX;
import static com.exposit.kickerapp.Constant.MAILGUN_SUBJECT;
import static com.exposit.kickerapp.Constant.MAILGUN_TO;
import static com.exposit.kickerapp.Constant.MAILGUN_USERNAME;

@Service("mailGunSender")
public class MailGunSender implements Sender
{

	private Logger LOGGER = LoggerFactory.getLogger(MailGunSender.class);

	@Value("${mailgun.api-key}")
	private String mailGunApiKey;
	@Value("${mailgun.domain}")
	String mailGunDomain;

	@Override
	public int send(EmailRequest emailRequest)
	{
		LOGGER.info("Start sending email by MailGun API");
		Client client = Client.create();
		client.addFilter(new HTTPBasicAuthFilter(MAILGUN_USERNAME, mailGunApiKey));


		WebResource webResource = client.resource(MAILGUN_BASE_URL + mailGunDomain + MAILGUN_MESSAGES);
		MultivaluedMapImpl formData = new MultivaluedMapImpl();
		formData.add(MAILGUN_FROM, emailRequest.getFrom() + MAILGUN_PREFIX + mailGunDomain + MAILGUN_POSTFIX);
		formData.add(MAILGUN_TO, emailRequest.getTo());
		formData.add(MAILGUN_SUBJECT, emailRequest.getSubject());
		formData.add(MAILGUN_CONTENT, emailRequest.getContent());
		ClientResponse post = webResource.type(MediaType.APPLICATION_FORM_URLENCODED).post(ClientResponse.class, formData);
		return post.getStatus();
	}
}
