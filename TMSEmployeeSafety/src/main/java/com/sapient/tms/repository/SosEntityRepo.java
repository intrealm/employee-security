package com.sapient.tms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sapient.tms.user.entity.SOSEntity;

public interface SosEntityRepo extends JpaRepository<SOSEntity,Integer>{

	List<SOSEntity> findByResolved(boolean b);

}
