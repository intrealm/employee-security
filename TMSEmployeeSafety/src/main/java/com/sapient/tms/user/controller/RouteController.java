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
	public List<RouteSequenceEntity> displayRoute(@PathVariable(name = "userName") String userName) {
		final List<RouteEntity> routes=routeRepository.findAllInactiveRoutesForUser(userName,false,false);
		if(!CollectionUtils.isEmpty(routes))
		{
		int firstRouteId=routes.iterator().next().getId();
		final List<RouteSequenceEntity> activeRouteSequence = this.routeSequenceRepository.findByRouteId(firstRouteId);
		return activeRouteSequence;
		}
		return null;
	}
	
	@RequestMapping(value = "/allActiveRoutes", method = RequestMethod.GET)
	public List<RouteEntity> getAllRoute() {
		return routeRepository.findAllRoutesBasedOnStartedAndCompletedFlag(true,false);	
	}
	
	@RequestMapping(value = "/displayRouteForAdmin/{routeId}", method = RequestMethod.GET)
	public List<RouteSequenceEntity> displayRoute(@PathVariable(name = "routeId") int routeId) {
		final List<RouteSequenceEntity> activeRoutes = this.routeSequenceRepository.findByRouteId(routeId);	
		return activeRoutes;
	}
	
}
