package com.sapient.tms.calling.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sapient.tms.dao.impl.RaiseSosDaoImpl;

@Component
public class RaiseSosImpl {
	
	@Autowired
	private RaiseSosDaoImpl raiseSosDaoImpl;
	
	public String raiseSosService(String username, int routeNumber) {
		boolean output = raiseSosDaoImpl.createSos(username, routeNumber);
		if(output) {
			// send SMS
			return "SOS Raised";
		} else {
			return "SOS Not Raised";
		}
		
	}
}
