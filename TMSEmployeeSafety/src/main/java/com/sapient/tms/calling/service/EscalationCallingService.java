package com.sapient.tms.calling.service;

import com.sapient.tms.user.entity.RouteSequenceEntity;

public interface EscalationCallingService
{
	  public void callOnEscalationMatrix(RouteSequenceEntity routeSequence);
}