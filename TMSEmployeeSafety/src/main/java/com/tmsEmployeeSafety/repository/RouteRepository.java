package com.tmsEmployeeSafety.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmsEmployeeSafety.user.entity.RouteEntity;
import com.tmsEmployeeSafety.user.entity.RouteSequenceEntity;
import com.tmsEmployeeSafety.user.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
public interface RouteRepository 
extends  JpaRepository<RouteSequenceEntity, String>

{
	 @Query(value="SELECT rs FROM RouteSequenceEntity rs JOIN RouteEntity re ON rs.routeId=re.id where rs.userName=:userName and re.isStarted is :isStarted",
	            nativeQuery=false
			    )
	 List<RouteSequenceEntity> findByIdAndIsStartedIn(String userName,boolean isStarted);

	 RouteSequenceEntity findByUserNameAndRouteIdIn(String userName, int routeId);
 }
