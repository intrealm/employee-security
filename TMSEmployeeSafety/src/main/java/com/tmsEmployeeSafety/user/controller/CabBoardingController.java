package com.tmsEmployeeSafety.user.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tmsEmployeeSafety.repository.RouteRepository;
import com.tmsEmployeeSafety.repository.UserRepository;
import com.tmsEmployeeSafety.user.entity.RouteEntity;
import com.tmsEmployeeSafety.user.entity.RouteSequenceEntity;
import com.tmsEmployeeSafety.user.entity.UserEntity;

@RestController
public class CabBoardingController {
	@Autowired
	private RouteRepository routeRepository;   
    @RequestMapping(value = "/board/{userName}/{routeId}", method = RequestMethod.GET)
    public boolean boardCab(@PathVariable(name="userName") String userName, @PathVariable(name="routeId") int routeId) {
    	final RouteSequenceEntity employeeRouteSequence=
    			this.routeRepository.findByUserNameAndRouteIdIn(userName, routeId);
    	if(null != employeeRouteSequence)
    	{
    	employeeRouteSequence.setBoarded(true);
    	routeRepository.save(employeeRouteSequence);
    	return true;
    	}
    	return false;
}
}
	    
