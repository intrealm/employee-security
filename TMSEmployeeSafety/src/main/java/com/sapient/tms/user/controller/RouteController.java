package com.sapient.tms.user.controller;

import java.util.List;

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
import com.sapient.tms.user.entity.RouteSequenceEntity;

@CrossOrigin
@RestController
public class RouteController {

	@Autowired
	private RouteSequenceRepository routeSequenceRepository;
	
	@Autowired
	private RouteRepository routeRepository;

	@RequestMapping(value = "/displayRoute/{userName}", method = RequestMethod.GET)
	public RouteSequenceEntity displayRoute(@PathVariable(name = "userName") String userName) {
		final List<RouteSequenceEntity> activeRoutes = this.routeSequenceRepository.findByIdAndIsStartedIn(userName, false);
		if (!CollectionUtils.isEmpty(activeRoutes)) {
			return activeRoutes.iterator().next();
		}
		return null;
	}
	
	@RequestMapping(value = "/allActiveRoutes/{guid}", method = RequestMethod.GET)
	public List<RouteEntity> getAllRoute(@PathVariable(name = "guid") String guid) {
		
		//validate guid as session id from cache
		return routeRepository.findAllActiveRoutes();
		
	}
}
