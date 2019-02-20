package com.tmsEmployeeSafety.repository;

import com.tmsEmployeeSafety.user.entity.RoleEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Long>{
}