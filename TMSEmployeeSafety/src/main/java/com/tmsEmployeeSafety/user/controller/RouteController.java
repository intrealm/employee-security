package com.tmsEmployeeSafety.user.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tmsEmployeeSafety.repository.RouteRepository;
import com.tmsEmployeeSafety.repository.UserRepository;
import com.tmsEmployeeSafety.user.entity.RouteEntity;
import com.tmsEmployeeSafety.user.entity.RouteSequenceEntity;
import com.tmsEmployeeSafety.user.entity.UserEntity;
@CrossOrigin
@RestController
public class RouteController {

	@Autowired
	private RouteRepository routeRepository;
		  @RequestMapping(value = "/displayRoute/{userName}", method = RequestMethod.GET)
		    public RouteSequenceEntity displayRoute(@PathVariable(name="userName") String userName) {
		    	final List<RouteSequenceEntity> activeRoutes=
		    			this.routeRepository.findByIdAndIsStartedIn(userName, false);
                if(!CollectionUtils.isEmpty(activeRoutes))
                {
		    	return activeRoutes.iterator().next();
                }
                return null;
}
}
	    
