package com.sapient.tms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sapient.tms.user.entity.UserLastLocationEntity;

public interface UserLocationRepository extends JpaRepository<UserLastLocationEntity, Integer> {

	UserLastLocationEntity findByUserId(Integer userId);
}
