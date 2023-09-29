package com.example.spring.service;

import com.amazonaws.services.iot.client.AWSIotException;
import com.amazonaws.services.iot.client.AWSIotQos;
import com.example.spring.config.MQTTConfig;
import com.example.spring.dto.IOTPayload;
import com.example.spring.dto.MyMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MqttPubSupService {

    @Autowired
    MQTTConfig mqttConfig;

    public void publishMessage(IOTPayload payload)  {
        mqttConfig.connectAwsIot();
        mqttConfig.publish(payload);
    }
}
