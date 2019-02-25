package com.sapient.tms.scheduler.service.impl;

import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sapient.tms.calling.service.impl.MakeCallServiceImpl;
import com.sapient.tms.repository.RouteRepository;
import com.sapient.tms.repository.RouteSequenceRepository;
import com.sapient.tms.repository.UserRepository;
import com.sapient.tms.scheduler.service.SchedulerService;
import com.sapient.tms.user.entity.RouteEntity;
import com.sapient.tms.user.entity.RouteSequenceEntity;
import com.sapient.tms.user.entity.UserEntity;
@Component
@Configuration
@PropertySource("classpath:configuration.properties")
public class SchedulerServiceImpl implements SchedulerService {
	Logger logger = Logger.getLogger(SchedulerServiceImpl.class.getName());
	@Autowired
	RouteRepository routeRepository;
	@Autowired
	RouteSequenceRepository routeSequenceRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	MakeCallServiceImpl makeCallService;
	
	@Value("${allowedDelayInMinutes}")
	long allowedDelayInMinutes;
	@Override
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
	
	
	@Override
	@Scheduled(fixedDelay = 10000)
	 public void scheduleJobIfEmployeeIsLate()
     {
   	  //This scheduler service marks the route if delayed
   	  List<RouteSequenceEntity> activeRouteSequences=routeSequenceRepository.getRouteSequenceListingWithTimeStamp();
   	  for(RouteSequenceEntity routeSequence:activeRouteSequences)
   	  {
   		  if(!routeSequence.isDeboarded())
   		  {
   		  //get Route details by route id
   		 Optional<RouteEntity> activeRoute= routeRepository.findById(routeSequence.getRouteId());
   		 if(activeRoute.isPresent())
   		 {
   		 Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
   		 long timeTakenInRoute = currentTimestamp.getMinutes()-activeRoute.get().getStartTime().getMinutes();
	   		  if(timeTakenInRoute>routeSequence.getEtaInMinutes().longValue())
   		  {
   			  long delayedBy=timeTakenInRoute-routeSequence.getEtaInMinutes();
   			  routeSequence.setDelayedBy(delayedBy);
   			  routeSequenceRepository.save(routeSequence);
   			  try {
				makeCallsToDelayedEmployees(routeSequence);
			} catch (URISyntaxException e) {
				logger.info("Error occurred while making a call");
			}
   		  }
   	  }
   	  }
   	  }
     }

	private void makeCallsToDelayedEmployees(RouteSequenceEntity routeSequence) throws URISyntaxException {
    if(routeSequence.getDelayedBy()>allowedDelayInMinutes)
    {
    	System.out.println("allowed delay in minutes"+allowedDelayInMinutes);
    	UserEntity employeeDetails=userRepository.findByUserName(routeSequence.getUserName());
    	makeCallService.setUpCall(employeeDetails.getPhoneNumber().toString(),routeSequence);
    }
	}
  
}
