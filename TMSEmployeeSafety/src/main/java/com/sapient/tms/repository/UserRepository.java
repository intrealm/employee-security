package com.sapient.tms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sapient.tms.user.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String> {
	UserEntity findByUserNameAndEncryptedPasswordIn(String id, String password);
}
