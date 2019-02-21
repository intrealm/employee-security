package com.sapient.tms.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.tms.repository.RouteRepository;
import com.sapient.tms.repository.RouteSequenceRepository;
import com.sapient.tms.user.entity.RouteEntity;
import com.sapient.tms.user.entity.RouteSequenceEntity;
@CrossOrigin
@RestController
public class StartCabController {
	@Autowired
	private RouteSequenceRepository routeSequenceRepository;
	
	@Autowired
	private RouteRepository routeRepository;

	@RequestMapping(value = "/startTrip/{routeId}", method = RequestMethod.GET)
	public boolean startTrip(@PathVariable(name = "routeId") String routeId) {
		if(!CollectionUtils.isEmpty(routeSequenceRepository.getBoardedEmployees(Integer.valueOf(routeId).intValue()))
		{
			RouteSequenceEntity routeEntity=routeRepository.findById(Integer.valueOf(routeId));
			routeEntity.setStartTime(new Timestamp(date.getTime()));
			routeRepository.save(routeEntity);
			//schedule google maps job from here to run every minute and save coordinates at the server
			
			//also schedule job to create cab is late by (every 2 mins).
			

			// the scheduler runs the calling service if ETA is extended by 10 minutes.	
		}
	}
}
