package com.sapient.tms.user.controller;

import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.tms.repository.RouteRepository;
import com.sapient.tms.repository.RouteSequenceRepository;
import com.sapient.tms.user.entity.RouteEntity;

@CrossOrigin
@RestController
public class CabController {
	@Autowired
	private RouteSequenceRepository routeSequenceRepository;
	
	@Autowired
	private RouteRepository routeRepository;
	
	@RequestMapping(value = "/startTrip/{routeId}", method = RequestMethod.GET)
	public boolean startTrip(@PathVariable(name = "routeId") int routeId) {
		System.out.println("heeeerrrree");
		if(!CollectionUtils.isEmpty(routeSequenceRepository.getBoardedEmployees(routeId)))
		{
			RouteEntity route=null;
			Optional<RouteEntity> routeEntity=routeRepository.findById(routeId);
			if(routeEntity.isPresent())
			{
				route=routeEntity.get();
			}
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			route.setStarted(true);
			route.setStartTime(timestamp);
			routeRepository.save(route);
			//schedule google maps job from here to run every minute and save coordinates at the server
			return true;
			//also schedule job to create cab is late by (every 2 mins).
			

			// the scheduler runs the calling service if ETA is extended by 10 minutes.	
		}
		return false;
	}
}
