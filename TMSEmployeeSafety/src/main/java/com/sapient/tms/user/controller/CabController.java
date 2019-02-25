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
			return true;
		}
		return false;
	}
	
	@RequestMapping(value = "/completeTrip/{routeId}", method = RequestMethod.GET)
	public boolean completeTrip(@PathVariable(name = "routeId") int routeId) {
		RouteEntity route=null;
		Optional<RouteEntity> routeEntity=routeRepository.findById(routeId);
		if(routeEntity.isPresent())
		{
			route=routeEntity.get();
			route.setCompleted(true);
			routeRepository.save(route);
			return true;
		}
		return false;
}
	
}
