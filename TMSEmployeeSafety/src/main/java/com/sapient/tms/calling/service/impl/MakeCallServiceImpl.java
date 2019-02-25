package com.sapient.tms.calling.service.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.sapient.tms.calling.service.EscalationCallingService;
import com.sapient.tms.calling.service.MakeCallService;
import com.sapient.tms.enums.CallTypeEnum;
import com.sapient.tms.repository.CallRepository;
import com.sapient.tms.repository.RouteSequenceRepository;
import com.sapient.tms.user.entity.CallEntity;
import com.sapient.tms.user.entity.RouteSequenceEntity;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.rest.api.v2010.account.CallFetcher;
import com.twilio.type.PhoneNumber;
import com.twilio.rest.api.v2010.account.Message;
@Configuration
@PropertySource("classpath:configuration.properties")
@Component
public class MakeCallServiceImpl implements MakeCallService {
	// Find your Account Sid and Token at twilio.com/user/account
	@Value("${twilioAccountSID}")
	public String ACCOUNT_SID;
	@Value("${twilioAuthToken}")
	public String AUTH_TOKEN;
	@Value("${allowedNumberOfCallsBeforeReachingEscaltionMatrix}")
	public int allowedNumberOfCalls;
	@Value("${allowedTimeInMinutesBetweenCalls}")
	public int allowedTimeInMinutesBetweenCalls;
	@Autowired
	CallRepository callRepository;
	@Autowired
	RouteSequenceRepository routeSequenceRepository;
	@Autowired
	EscalationCallingService escalationCallingService;
	Logger logger = Logger.getLogger(MakeCallServiceImpl.class.getName());
	@Override
	public void setUpCall(String employeePhoneNumber, RouteSequenceEntity routeSequence) {
		if (!routeSequence.isDeboarded()) {
			Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
			List<CallEntity> calls=checkIfCallWasTriggeredBefore(employeePhoneNumber,routeSequence,CallTypeEnum.NORMAL);
			if (calls.size() > allowedNumberOfCalls && !routeSequence.isDeboarded()) {
				// reach the escalation matrix
				escalationCallingService.callOnEscalationMatrix(routeSequence);
			} else if (calls.size() < allowedNumberOfCalls && !routeSequence.isDeboarded()) {
				if (!CollectionUtils.isEmpty(calls)) {
					Timestamp callTime = calls.get(0).getCallTime();
					Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
					@SuppressWarnings("deprecation")
					long minutesBetweenCalls = currentTimestamp.getMinutes() - callTime.getMinutes();
					if (minutesBetweenCalls > allowedTimeInMinutesBetweenCalls) {
						makeCall(employeePhoneNumber, routeSequence,CallTypeEnum.NORMAL);
					}
				} else {
					makeCall(employeePhoneNumber, routeSequence,CallTypeEnum.NORMAL);
				}
			}
		}
	}

	@Override
	public List<CallEntity> checkIfCallWasTriggeredBefore(String employeePhoneNumber, RouteSequenceEntity routeSequence,
			CallTypeEnum callEnum) {
		List<CallEntity> calls = callRepository.findCallsIfTriggeredAfterFixedTime(employeePhoneNumber,callEnum);
		for (CallEntity call : calls) {
			CallFetcher fetchedCall = Call.fetcher(call.getSid());
			Call madeCall = fetchedCall.fetch(Twilio.getRestClient());
			if (madeCall.getStatus() == Call.Status.COMPLETED) {
				routeSequence.setDeboarded(true);
				routeSequenceRepository.save(routeSequence);
			}
		}
		return calls;
	}

	
	
	@Override
	public void makeCall(String employeePhoneNumber, RouteSequenceEntity routeSequence, CallTypeEnum callType) {
		Call call;
		try {
			call = Call.creator(new PhoneNumber("+91" + employeePhoneNumber), new PhoneNumber("+13126472097"),
					new URI("http://demo.twilio.com/docs/voice.xml")).create();
			/*
			 * Message.creator(new PhoneNumber("+whatsapp:+14155238886"), new
			 * PhoneNumber("+whatsapp:+918860095906"),
			 * "Please mark yourself as deboarded if you have reached home safely")
			 * .create();
			 */
			logger.info("Call has been triggered to "+employeePhoneNumber+" of employee "+routeSequence.getUserName());
			addCallToRepository(call, employeePhoneNumber, routeSequence,callType);
		} catch (URISyntaxException e) {
			logger.info("Error while making call to : " + employeePhoneNumber);
		}

	}

	private void addCallToRepository(Call call, String employeePhoneNumber, RouteSequenceEntity routeSequence, CallTypeEnum callType) {
		CallEntity callEntity = new CallEntity();
		Timestamp callTimeStamp = new Timestamp(System.currentTimeMillis());
		callEntity.setSid(call.getSid());
		callEntity.setUserName(routeSequence.getUserName());
		callEntity.setMobileNumber(employeePhoneNumber);
		callEntity.setRouteId(routeSequence.getRouteId());
		callEntity.setCallTime(callTimeStamp);
		callEntity.setCallType(callType);
		callRepository.save(callEntity);
	}

}
