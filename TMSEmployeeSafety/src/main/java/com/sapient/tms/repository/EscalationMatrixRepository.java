package com.sapient.tms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sapient.tms.user.entity.EscalationMatrixEntity;

public interface EscalationMatrixRepository extends JpaRepository<EscalationMatrixEntity, String> {
		List<EscalationMatrixEntity> findByUserName(String userName);
}

