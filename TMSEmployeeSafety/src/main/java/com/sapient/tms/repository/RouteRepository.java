package com.sapient.tms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sapient.tms.user.entity.RouteEntity;

public interface RouteRepository extends JpaRepository<RouteEntity, Integer>

{
	@Query(value = "FROM RouteEntity where isStarted = true and isCompleted = false")
	List<RouteEntity> findAllActiveRoutes();

}
