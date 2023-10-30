package com.example.spring.repository;

import com.example.spring.entity.LightLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LightRepository extends JpaRepository<LightLog,Long> {
}
