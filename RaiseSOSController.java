package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.RaiseSosImpl;

@RestController
public class RaiseSOSController {
	
	@Autowired
	private RaiseSosImpl raiseSosImpl;
	
	@RequestMapping(value="/raiseSOS", params = { "username", "routenumber" })
	public String raiseSos(@RequestParam(value="username") String userName, @RequestParam(value="routenumber") String routeNumber){
		int routeNo = Integer.parseInt(routeNumber);
		return raiseSosImpl.raiseSosService(userName,routeNo);
	}

}
