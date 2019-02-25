package com.sapient.tms.calling.service;

import java.util.List;

import com.sapient.tms.enums.CallTypeEnum;
import com.sapient.tms.user.entity.CallEntity;
import com.sapient.tms.user.entity.RouteSequenceEntity;

public interface MakeCallService
{

	void makeCall(String employeePhoneNumber, RouteSequenceEntity routeSequence, CallTypeEnum callType);

	List<CallEntity> checkIfCallWasTriggeredBefore(String employeePhoneNumber, RouteSequenceEntity routeSequence,
			CallTypeEnum callEnum);

	void setUpCall(String employeePhoneNumber, RouteSequenceEntity routeSequence);
	 
}