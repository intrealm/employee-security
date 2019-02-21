package com.sapient.tms.calling.service;

import com.twilio.type.PhoneNumber;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Component;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Call;
@Component
public class MakeCallService {
	// Find your Account Sid and Token at twilio.com/user/account
	public static final String ACCOUNT_SID = "AC4a231dc73b187e65cdb09e336e70912b";
	public static final String AUTH_TOKEN = "17a0b42a7e2da587cb7c9ce67c9b2322";

	public  void makeCall() throws URISyntaxException {
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		 Call call=Call.creator(new PhoneNumber("+918585912788"), new PhoneNumber("+13126472097"), new URI("http://demo.twilio.com/docs/voice.xml")).create();
	 System.out.println(call.getSid());
	}
}

