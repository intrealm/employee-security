package com.sapient.tms.scheduler.service.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.sapient.tms.repository.RouteRepository;
import com.sapient.tms.scheduler.service.SchedulerService;
import com.sapient.tms.user.entity.RouteEntity;

public class SchedulerServiceImpl implements SchedulerService {
	@Autowired
	RouteRepository routeRepository;
	@Scheduled(fixedDelay = 10000)
      public void scheduleJobIfCabIsLate()
      {
    	  //This scheduler service marks the route if delayed
    	  List<RouteEntity> activeRoutes=routeRepository.getRouteListingWithTimeStamp();
    	  for(RouteEntity route:activeRoutes)
    	  {
    		  Calendar currentCalendar = Calendar.getInstance();
			  long timeTakenInRoute = route.getStartTime().getTime()-currentCalendar.getTimeInMillis();
    		  if((timeTakenInRoute/60000)>route.getEtaInMinutes())
    		  {
    			  long delayedBy=timeTakenInRoute/60000-route.getEtaInMinutes();
    			  route.setDelayedBy(delayedBy);
    			  routeRepository.save(route);
    		  }
    	  }
      }
  
}
