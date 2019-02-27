package com.sapient.tms.calling.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sapient.tms.dao.impl.RaiseSosDaoImpl;
import com.sapient.tms.enums.ShiftEnum;
import com.sapient.tms.repository.RouteRepository;
import com.sapient.tms.repository.SosEntityRepo;
import com.sapient.tms.repository.UserLocationRepository;
import com.sapient.tms.repository.UserRepository;
import com.sapient.tms.user.entity.RouteEntity;
import com.sapient.tms.user.entity.SOSEntity;
import com.sapient.tms.user.entity.UserEntity;
import com.sapient.tms.user.entity.UserLastLocationEntity;

@Component
public class RaiseSosImpl {
	
	@Autowired
	private RaiseSosDaoImpl raiseSosDaoImpl;
	
	@Autowired
	private SosEntityRepo sosEntityRepo;
	
	@Autowired
	private RouteRepository routeRepository;
	
	@Autowired
	private UserLocationRepository userLocationRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public String raiseSosService(String username, int routeNumber) {
		boolean output = raiseSosDaoImpl.createSos(username, routeNumber);
		if(output) {
			return "SOS Raised";
		} else {
			return "SOS Not Raised";
		}
	}
	
	public JSONObject fetchSosData(String sosid)
	{
		SOSEntity sosEntity = sosEntityRepo.findById(Integer.parseInt(sosid)).orElse(null);
		
		if(sosEntity==null)
			return new JSONObject();
		
		RouteEntity routeEntity = routeRepository.findById(sosEntity.getRouteId()).orElse(null);
		
		UserEntity userEntity = userRepository.findByUserName(sosEntity.getUserName());
		
		UserLastLocationEntity userLocationEntity = userLocationRepository.findByUserId(Integer.parseInt(userEntity.getId()));
		
		
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("routeid", ""+sosEntity.getRouteId());
			
			jsonObject.put("username", userEntity.getName());
			if(ShiftEnum.DROP.equals(routeEntity.getShift()))
				jsonObject.put("shift", "DROP");
			else
				jsonObject.put("shift", "PICKUP");
			jsonObject.put("phonenumber", userEntity.getPhoneNumber());
			jsonObject.put("lat", userLocationEntity.getLat());
			jsonObject.put("long", userLocationEntity.getLon());
			return jsonObject;
		
		} catch (JSONException e) {
			e.printStackTrace();
		}	
		return new JSONObject();
	}

	public List<JSONObject> getResolvedSOSRequests(boolean b) {
		List<SOSEntity> sosList=sosEntityRepo.findByResolved(false);
		final List<JSONObject> sosObjects=new ArrayList();
		for(SOSEntity sos:sosList)
		{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("userName",sos.getUserName());
			jsonObject.put("id", sos.getId());
			jsonObject.put("raisedAt",sos.getRaisedAt());
			jsonObject.put("raisedOn",sos.getRaisedOn());
			jsonObject.put("resolvedAt", sos.getResolvedAt());
			jsonObject.put("resolvedOn", sos.getResolvedOn());
			jsonObject.put("routeId", sos.getRouteId());
			Optional<RouteEntity> routeEntity=routeRepository.findById(sos.getRouteId());
			if(routeEntity.get()!=null)
			{
			jsonObject.put("routeNumber", routeEntity.get().getRouteNumber());
			}
			sosObjects.add(jsonObject);
		}
		catch (JSONException e) {
			e.printStackTrace();
		}
	}
		return sosObjects;
	}
}