package com.sapient.tms.call.controller;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.sapient.tms.calling.service.MakeCallService;
@CrossOrigin
@RestController
public class AutomatedCallingController {
	@Autowired
	private MakeCallService makeCallService;
	
	@RequestMapping(value = "/makeCall", method = RequestMethod.GET)
	public void makeCall() throws URISyntaxException
	{
		makeCallService.makeCall(); 
	}
}
