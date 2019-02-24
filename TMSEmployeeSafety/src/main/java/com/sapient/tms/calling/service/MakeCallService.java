package com.sapient.tms.calling.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.sapient.tms.repository.CallRepository;
import com.sapient.tms.repository.RouteRepository;
import com.sapient.tms.repository.RouteSequenceRepository;
import com.sapient.tms.scheduler.service.impl.SchedulerServiceImpl;
import com.sapient.tms.user.entity.CallEntity;
import com.sapient.tms.user.entity.RouteSequenceEntity;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.rest.api.v2010.account.CallFetcher;
import com.twilio.rest.taskrouter.v1.workspace.task.Reservation.CallStatus;
import com.twilio.type.PhoneNumber;

@Configuration
@PropertySource("classpath:configuration.properties")
@Component
public class MakeCallService {
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
	Logger logger = Logger.getLogger(MakeCallService.class.getName());

	public void setUpCall(String employeePhoneNumber, RouteSequenceEntity routeSequence) {
		if (!routeSequence.isDeboarded()) {
			// pick data from db
			List<CallEntity> calls = callRepository.findCallsIfTriggeredAfterFixedTime(employeePhoneNumber);
			for (CallEntity call : calls) {
				CallFetcher fetchedCall = Call.fetcher(call.getSid());
				Call madeCall = fetchedCall.fetch();
				if (madeCall.getStatus() == Call.Status.COMPLETED) {
					routeSequence.setDeboarded(true);
					routeSequenceRepository.save(routeSequence);
				}
			}
			System.out.println("size of call is"+calls.size());
			if (calls.size() > allowedNumberOfCalls && !routeSequence.isDeboarded()) {
				// reach the escalation matrix
				System.out.println("no call");
			} else if (calls.size() < allowedNumberOfCalls && !routeSequence.isDeboarded()) {
				if (!CollectionUtils.isEmpty(calls)) {
					Timestamp callTime = calls.get(0).getCallTime();
					Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
					@SuppressWarnings("deprecation")
					long minutesBetweenCalls = currentTimestamp.getMinutes() - callTime.getMinutes();
					System.out.println("the minutes between calls" + minutesBetweenCalls + " allowed"
							+ allowedTimeInMinutesBetweenCalls);
					if (minutesBetweenCalls > allowedTimeInMinutesBetweenCalls) {
						System.out.println("second call");
						makeCall(employeePhoneNumber, routeSequence);
					}
				} else {
					System.out.println("first call");
					makeCall(employeePhoneNumber, routeSequence);
				}
			}
		}
	}

	private void makeCall(String employeePhoneNumber, RouteSequenceEntity routeSequence) {
		System.out.println("call has been triggered");

		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		Call call;
		try {
			call = Call.creator(new PhoneNumber("+91" + employeePhoneNumber), new PhoneNumber("+13126472097"),
					new URI("http://demo.twilio.com/docs/voice.xml")).create();

			addCallToRepository(call, employeePhoneNumber, routeSequence);
		} catch (URISyntaxException e) {
			logger.info("Error while making call to : " + employeePhoneNumber);
		}

	}

	private void addCallToRepository(Call call, String employeePhoneNumber, RouteSequenceEntity routeSequence) {
		CallEntity callEntity = new CallEntity();
		Timestamp callTimeStamp = new Timestamp(System.currentTimeMillis());
		callEntity.setSid(call.getSid());
		callEntity.setUserName(routeSequence.getUserName());
		callEntity.setMobileNumber(employeePhoneNumber);
		callEntity.setRouteId(routeSequence.getRouteId());
		callEntity.setCallTime(callTimeStamp);
		callRepository.save(callEntity);
	}

}
