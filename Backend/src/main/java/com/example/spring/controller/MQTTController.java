package com.example.spring.controller;

import com.example.spring.dto.IOTPayload;
import com.example.spring.encrypt.AES;
import com.example.spring.payload.request.LedStatus;
import com.example.spring.service.MqttPubSupService;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPublish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MQTTController {

    @Autowired
    PasswordEncoder encoder;
    @Autowired
    MqttPubSupService mqttPubSupService;
    @Value("${aes.key.secret}")
    private String aesKeySecret;
    @PostMapping("/publish")
    public  String publishMessage(@RequestBody IOTPayload payload){
        mqttPubSupService.publishMessage(payload);
        return "message published successfully";
    }
}
