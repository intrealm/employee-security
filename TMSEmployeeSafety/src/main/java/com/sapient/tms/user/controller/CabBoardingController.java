package com.sapient.tms.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.tms.repository.RouteRepository;
import com.sapient.tms.user.entity.RouteSequenceEntity;

@CrossOrigin
@RestController
public class CabBoardingController {
	@Autowired
	private RouteRepository routeRepository;

	@RequestMapping(value = "/board/{userName}/{routeId}", method = RequestMethod.GET)
	public boolean boardCab(@PathVariable(name = "userName") String userName,
			@PathVariable(name = "routeId") int routeId) {
		final RouteSequenceEntity employeeRouteSequence = this.routeRepository.findByUserNameAndRouteIdIn(userName,
				routeId);
		if (null != employeeRouteSequence) {
			employeeRouteSequence.setBoarded(true);
			routeRepository.save(employeeRouteSequence);
			return true;
		}
		return false;
	}
}
