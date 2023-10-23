package com.example.spring.repository;

import com.example.spring.entity.DataSensorDHT11;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataSensorDHT11Repository extends JpaRepository<DataSensorDHT11,Long> {

}
