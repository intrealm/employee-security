package com.sapient.tms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sapient.tms.user.entity.RouteEntity;

public interface RouteRepository extends JpaRepository<RouteEntity, Integer>

{
	@Query(value = "select re FROM RouteEntity re JOIN RouteSequenceEntity rs ON re.id=rs.routeId where re.isStarted = :isStarted and re.isCompleted = :isCompleted and rs.userName=:userName")
	List<RouteEntity> findAllInactiveRoutesForUser(String userName, boolean isStarted,boolean isCompleted);
    
	Optional<RouteEntity> findById(int id);
	
	@Query(value = "FROM RouteEntity where isStarted = true and isCompleted = false and startTime is not null")
	List<RouteEntity> getRouteListingWithTimeStamp();

	@Query(value = "FROM RouteEntity where isStarted = :isStarted and isCompleted = :isCompleted")
	List<RouteEntity> findAllRoutesBasedOnStartedAndCompletedFlag(boolean isStarted, boolean isCompleted);
	/*
	 * @Query(value = "select routeNumber FROM RouteEntity where id = :isStarted")
	 * int findByRouteId(int id);
	 */
}
