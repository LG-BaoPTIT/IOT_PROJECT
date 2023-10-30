package com.example.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.spring.entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer>{
	
	Users findByUserName(String userName);
	boolean existsByUserName(String userName);
	boolean existsByEmail(String email);
	@Query(value = "SELECT device_id FROM users WHERE user_name = :userName", nativeQuery = true)
	String getDeviceIdByUserName(@Param("userName") String userName);
}
