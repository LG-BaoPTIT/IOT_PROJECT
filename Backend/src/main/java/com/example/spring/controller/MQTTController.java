package com.example.spring.controller;

import com.example.spring.payload.request.LedStatus;
import com.example.spring.service.MQTTService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MQTTController {
    @Autowired
    MQTTService mqttService;

    @Autowired
    ObjectMapper objectMapper;

    @PostMapping("/publish")
    public String publishMessage(@RequestBody LedStatus ledStatus) {
        try {
            String topic = "led_state";
            String payload = objectMapper.writeValueAsString(ledStatus);
            mqttService.sendMessage(topic, "1");
            return "Message published successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error publishing message: " + e.getMessage();
        }
    }

    @PostMapping("/status_led")
    public String publishMessageStatusLed(@RequestBody LedStatus ledStatus) {
        try {
            String topic = "led_state";
            String payload = objectMapper.writeValueAsString(ledStatus);
            mqttService.sendMessage(topic, ledStatus.getLedStatus());
            return "Message published successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error publishing message: " + e.getMessage();
        }
    }
}
