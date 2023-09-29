package com.example.spring.controller;

import com.example.spring.service.MqttPubSupService;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPublish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MQTTController {

    @Autowired
    MqttPubSupService mqttPubSupService;

    @PostMapping("/publish")
    public  String publishMessage(){
        mqttPubSupService.publishMessage();
        return "message published successfully";
    }
}
