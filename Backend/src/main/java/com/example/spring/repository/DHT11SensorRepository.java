package com.example.spring.repository;

import com.example.spring.entity.DHT11Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DHT11SensorRepository extends JpaRepository<DHT11Sensor,Long> {

}
