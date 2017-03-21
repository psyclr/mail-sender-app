package com.exposit.kickerapp.model;

import com.exposit.kickerapp.request.EmailRequest;

public class Email
{
	private String from;
	private String subject;
	private String to;
	private String content;

	public Email(EmailRequest emailRequest)
	{
		this.from = emailRequest.getFrom();
		this.subject = emailRequest.getSubject();
		this.to = emailRequest.getTo();
		this.content = emailRequest.getContent();

	}

	public String getFrom()
	{
		return from;
	}

	public void setFrom(String from)
	{
		this.from = from;
	}

	public String getSubject()
	{
		return subject;
	}

	public void setSubject(String subject)
	{
		this.subject = subject;
	}

	public String getTo()
	{
		return to;
	}

	public void setTo(String to)
	{
		this.to = to;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}
}
