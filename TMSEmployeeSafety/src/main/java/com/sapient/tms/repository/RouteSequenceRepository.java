package com.sapient.tms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sapient.tms.user.entity.RouteSequenceEntity;

public interface RouteSequenceRepository extends JpaRepository<RouteSequenceEntity, String>

{
	@Query(value = "SELECT rs FROM RouteSequenceEntity rs JOIN RouteEntity re ON rs.routeId=re.id where rs.userName=:userName and re.isStarted is :isStarted", nativeQuery = false)
	List<RouteSequenceEntity> findByIdAndIsStartedIn(String userName, boolean isStarted);

	RouteSequenceEntity findByUserNameAndRouteIdIn(String userName, int routeId);
	
	@Query(value = "SELECT rs FROM RouteSequenceEntity rs where rs.boarded is true", nativeQuery = false)
	List<RouteSequenceEntity> getBoardedEmployees(int routeId);
}
