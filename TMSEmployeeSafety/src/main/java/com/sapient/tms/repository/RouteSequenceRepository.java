package com.sapient.tms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sapient.tms.user.entity.RouteEntity;
import com.sapient.tms.user.entity.RouteSequenceEntity;

public interface RouteSequenceRepository extends JpaRepository<RouteSequenceEntity, String>

{

	RouteSequenceEntity findByUserNameAndRouteIdIn(String userName, int routeId);
	
	@Query(value = "SELECT rs FROM RouteSequenceEntity rs where rs.boarded = 0 and rs.routeId=:routeId", nativeQuery = false)
	List<RouteSequenceEntity> getBoardedEmployees(int routeId);
	
	@Query(value = "SELECT rs FROM RouteSequenceEntity rs join RouteEntity re ON rs.routeId=re.id where rs.boarded = true and rs.deboarded = false and re.startTime is not null")
	List<RouteSequenceEntity> getRouteSequenceListingWithTimeStamp();

	List<RouteSequenceEntity> findByRouteId(int routeId);
	
}
