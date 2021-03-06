package com.tmsEmployeeSafety.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.tmsEmployeeSafety.user.entity.RoleEntity;
import com.tmsEmployeeSafety.user.entity.UserEntity;


public interface UserRepository 
extends  JpaRepository<UserEntity, String>
{
	 UserEntity findUserById(String id);
 }
