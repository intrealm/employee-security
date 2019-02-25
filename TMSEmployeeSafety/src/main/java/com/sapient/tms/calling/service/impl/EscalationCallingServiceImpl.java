package com.sapient.tms.calling.service.impl;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

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
import com.sapient.tms.repository.EscalationMatrixRepository;
import com.sapient.tms.user.entity.CallEntity;
import com.sapient.tms.user.entity.EscalationMatrixEntity;
import com.sapient.tms.user.entity.RouteSequenceEntity;
@Configuration
@PropertySource("classpath:configuration.properties")
@Component
public class EscalationCallingServiceImpl implements EscalationCallingService {
	@Autowired
	EscalationMatrixRepository escalationMatrixRepository;
	@Autowired
	MakeCallService makeCallService;
	@Autowired
	CallRepository callRepository;
	@Value("${allowedNumberOfCallsBeforeReachingEscaltionMatrix}")
	public int allowedNumberOfCalls;
	@Value("${allowedTimeInMinutesBetweenCalls}")
	public int allowedTimeInMinutesBetweenCalls;
	public EscalationMatrixEntity getEscalationPhoneNumbersByUserName(String userName)
	{
		List<EscalationMatrixEntity> escalationEntityList=escalationMatrixRepository.findByUserName(userName);
		return escalationEntityList.iterator().next();
	}
    public void callOnEscalationMatrix(RouteSequenceEntity routeSequence)
    {
    	EscalationMatrixEntity escalationEntity=getEscalationPhoneNumbersByUserName(routeSequence.getUserName());
    	List<String> escalationPhoneNumbers = Arrays.asList(escalationEntity.getEscalationPhoneNumbers().split(","));
    	for(String phoneNumber:escalationPhoneNumbers)
    	{
            if(!routeSequence.isDeboarded())
            {
    		List<CallEntity> callsMade=makeCallService.checkIfCallWasTriggeredBefore(phoneNumber, routeSequence, CallTypeEnum.ESCALATION);
    		if (callsMade.size() < allowedNumberOfCalls && !routeSequence.isDeboarded()) {
				if (!CollectionUtils.isEmpty(callsMade)) {
					Timestamp callTime = callsMade.get(0).getCallTime();
					Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
					@SuppressWarnings("deprecation")
					long minutesBetweenCalls = currentTimestamp.getMinutes() - callTime.getMinutes();
					if (minutesBetweenCalls > allowedTimeInMinutesBetweenCalls) {
						makeCallService.makeCall(phoneNumber, routeSequence,CallTypeEnum.ESCALATION);
					}
				}
				else
				{
					makeCallService.makeCall(phoneNumber, routeSequence,CallTypeEnum.ESCALATION);
				}
            }
    	}
    }
}
}