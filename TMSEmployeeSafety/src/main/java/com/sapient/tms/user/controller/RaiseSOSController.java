package com.sapient.tms.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.tms.calling.service.impl.RaiseSosImpl;
import com.sapient.tms.repository.SosEntityRepo;
import com.sapient.tms.user.entity.SOSEntity;

@RestController
public class RaiseSOSController {
	
	@Autowired
	private RaiseSosImpl raiseSosImpl;
	
	@Autowired
	private SosEntityRepo sosEntityRepo;
	
	@RequestMapping(value="/raiseSOS/{username}/{routeId}", method = RequestMethod.GET)
	public String raiseSos(@PathVariable(value="username") String userName, @PathVariable(value="routeId") String routeId){
		int routeNo = Integer.parseInt(routeId);
		return raiseSosImpl.raiseSosService(userName,routeNo);
	}
	
	@RequestMapping(value="/fetchSOSRequests", method = RequestMethod.GET)
	public List<SOSEntity> fetchSOSActiveRequests()
	{
		return sosEntityRepo.findByResolved(false);
	}
	
	@RequestMapping(value="/resolveSOSRequest", method = RequestMethod.GET)
	public List<SOSEntity> resolveSOSRequest()
	{
		return sosEntityRepo.findByResolved(false);
	}
  
}
