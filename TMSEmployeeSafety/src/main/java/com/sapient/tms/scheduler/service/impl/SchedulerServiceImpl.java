package com.sapient.tms.scheduler.service.impl;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sapient.tms.repository.RouteRepository;
import com.sapient.tms.repository.RouteSequenceRepository;
import com.sapient.tms.scheduler.service.SchedulerService;
import com.sapient.tms.user.entity.RouteEntity;
import com.sapient.tms.user.entity.RouteSequenceEntity;
@Component
public class SchedulerServiceImpl implements SchedulerService {
	@Autowired
	RouteRepository routeRepository;
	@Autowired
	RouteSequenceRepository routeSequenceRepository;
	@Scheduled(fixedDelay = 10000)
      public void scheduleJobIfCabIsLate()
      {
    	  //This scheduler service marks the route if delayed
    	  List<RouteEntity> activeRoutes=routeRepository.getRouteListingWithTimeStamp();
    	  for(RouteEntity route:activeRoutes)
    	  {
    		  Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
			  @SuppressWarnings("deprecation")
			  long timeTakenInRoute = currentTimestamp.getMinutes()-route.getStartTime().getMinutes();
    		  if(timeTakenInRoute>route.getEtaInMinutes())
    		  {
    			  long delayedBy=timeTakenInRoute-route.getEtaInMinutes();
    			  route.setDelayedBy(delayedBy);
    			  routeRepository.save(route);
    		  }
    	  }
      }
	
	@Scheduled(fixedDelay = 10000)
	 public void scheduleJobIfEmployeeIsLate()
     {
   	  //This scheduler service marks the route if delayed
   	  List<RouteSequenceEntity> activeRouteSequences=routeSequenceRepository.getRouteSequenceListingWithTimeStamp();
   	  for(RouteSequenceEntity routeSequence:activeRouteSequences)
   	  {
   		  //get Route details by route id
   		 Optional<RouteEntity> activeRoute= routeRepository.findById(routeSequence.getRouteId());
   		 if(activeRoute.isPresent())
   		 {
   		//  Calendar currentCalendar = Calendar.getInstance();
   		 Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
   		 long timeTakenInRoute = currentTimestamp.getMinutes()-activeRoute.get().getStartTime().getMinutes();
	   		  if(timeTakenInRoute>routeSequence.getEtaInMinutes().longValue())
   		  {
   			  long delayedBy=timeTakenInRoute-routeSequence.getEtaInMinutes();
   			  routeSequence.setDelayedBy(delayedBy);
   			  routeSequenceRepository.save(routeSequence);
   		  }
   	  }
   	  }
     }
  
}
