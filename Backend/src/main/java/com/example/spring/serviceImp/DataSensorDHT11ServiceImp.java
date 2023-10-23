package com.example.spring.serviceImp;

import com.example.spring.entity.DataSensorDHT11;
import com.example.spring.repository.DataSensorDHT11Repository;
import com.example.spring.service.DataSensorDHT11Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataSensorDHT11ServiceImp implements DataSensorDHT11Service {
    @Autowired
    DataSensorDHT11Repository dht11Repository;


    @Override
    public DataSensorDHT11 save(DataSensorDHT11 dataSensorDHT11) {
        return dht11Repository.save(dataSensorDHT11);
    }
}
