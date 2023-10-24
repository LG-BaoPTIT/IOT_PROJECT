package com.example.spring.serviceImp;

import com.example.spring.entity.DHT11Sensor;
import com.example.spring.repository.DHT11SensorRepository;
import com.example.spring.service.SensorDHT11SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SensorDHT11SensorServiceImp implements SensorDHT11SensorService {
    @Autowired
    DHT11SensorRepository dht11Repository;


    @Override
    public DHT11Sensor save(DHT11Sensor DHT11Sensor) {
        return dht11Repository.save(DHT11Sensor);
    }
}
