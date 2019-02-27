package com.sapient.tms.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sapient.tms.user.entity.UserLastLocationEntity;

public interface UserLocationRepository extends JpaRepository<UserLastLocationEntity, Integer> {

	Optional<UserLastLocationEntity> findByUserId(Integer userId);

	@Transactional
	Integer deleteByUserId(Integer userId);

}
