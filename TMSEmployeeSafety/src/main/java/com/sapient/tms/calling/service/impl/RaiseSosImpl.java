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
	
	public String raiseSosService(String username, int routeNumber, String lat, String lon) {
		boolean output = raiseSosDaoImpl.createSos(username, routeNumber);
		if (lat != null && !lat.isEmpty() && lon != null && !lon.isEmpty()) {
			// update user
			UserEntity user = userRepository.findByUserName(username);
			Optional<UserLastLocationEntity> userLocationOpt = userLocationRepository
					.findByUserId(Integer.parseInt(user.getId()));
			UserLastLocationEntity userLocation = null;
			if (userLocationOpt.isPresent()) {
				userLocation = userLocationOpt.get();
			} else {
				userLocation = new UserLastLocationEntity();
				userLocation.setUserId(Integer.parseInt(user.getId()));
				userLocation.setLat(Double.parseDouble(lat));
				userLocation.setLon(Double.parseDouble(lon));
			}
			userLocationRepository.save(userLocation);
		}
		if(output) {
			return "SOS Raised";
		} else {
			return "SOS Not Raised";
		}
	}
	
	public JSONObject fetchSosData(String sosid) {
		SOSEntity sosEntity = sosEntityRepo.findById(Integer.parseInt(sosid)).orElse(null);
		
		if(sosEntity==null)
			return new JSONObject();
		
		RouteEntity routeEntity = routeRepository.findById(sosEntity.getRouteId()).orElse(null);
		
		UserEntity userEntity = userRepository.findByUserName(sosEntity.getUserName());
		
		Optional<UserLastLocationEntity> userLocationEntityoption = userLocationRepository
				.findByUserId(Integer.parseInt(userEntity.getId()));
		
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("routeid", ""+sosEntity.getRouteId());
			
			jsonObject.put("username", userEntity.getName());
			if(ShiftEnum.DROP.equals(routeEntity.getShift()))
				jsonObject.put("shift", "DROP");
			else
				jsonObject.put("shift", "PICKUP");
			jsonObject.put("phonenumber", userEntity.getPhoneNumber());
			if (userLocationEntityoption.isPresent()) {
				UserLastLocationEntity userLocation = userLocationEntityoption.get();
				jsonObject.put("lat", userLocation.getLat());
				jsonObject.put("long", userLocation.getLon());
			}

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