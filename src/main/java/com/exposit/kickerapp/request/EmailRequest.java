package com.exposit.kickerapp.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"from", "subject", "to", "content"})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class EmailRequest
{
	private String from;
	private String subject;
	private String to;
	private String content;

	@JsonProperty(value = "from")
	public String getFrom()
	{
		return from;
	}

	public void setFrom(String from)
	{
		this.from = from;
	}

	@JsonProperty(value = "subject")
	public String getSubject()
	{
		return subject;
	}

	public void setSubject(String subject)
	{
		this.subject = subject;
	}

	@JsonProperty(value = "to")
	public String getTo()
	{
		return to;
	}

	public void setTo(String to)
	{
		this.to = to;
	}

	@JsonProperty(value = "content")
	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}
}
