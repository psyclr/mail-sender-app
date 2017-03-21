package com.exposit.kickerapp.sender;

import com.exposit.kickerapp.request.EmailRequest;

public interface Sender
{
	int send(EmailRequest emailRequest);
}
