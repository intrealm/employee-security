package com.sapient.tms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sapient.tms.user.entity.CallEntity;

public interface CallRepository extends JpaRepository<CallEntity, String> {

	@Query(value = "SELECT ce FROM CallEntity ce where CAST(ce.callTime as date) =  CAST(getdate() as date) and ce.mobileNumber=:employeePhoneNumber order by ce.callTime desc", nativeQuery = false)
	List<CallEntity> findCallsIfTriggeredAfterFixedTime(String employeePhoneNumber);
}

