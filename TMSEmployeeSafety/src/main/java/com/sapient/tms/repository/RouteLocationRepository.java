package com.sapient.tms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sapient.tms.user.entity.CabLastLocationEntity;

public interface RouteLocationRepository extends JpaRepository<CabLastLocationEntity, Integer> {

	CabLastLocationEntity findByRouteId(Integer routeId);
}
