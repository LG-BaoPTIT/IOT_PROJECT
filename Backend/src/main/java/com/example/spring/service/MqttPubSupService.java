package com.example.spring.service;

import com.amazonaws.services.iot.client.AWSIotException;
import com.example.spring.config.MQTTConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MqttPubSupService {

    @Autowired
    MQTTConfig mqttConfig;
    public void publishMessage()  {
        mqttConfig.connectAwsIot();
    }
}
