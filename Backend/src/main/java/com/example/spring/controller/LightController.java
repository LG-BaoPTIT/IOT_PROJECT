package com.example.spring.controller;

import com.example.spring.entity.LightLog;
import com.example.spring.payload.request.LightStatus;
import com.example.spring.service.LightService;
import com.example.spring.service.MQTTService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.DataInput;
import java.io.IOException;
import java.util.Date;

@RestController
public class LightController {
    @Autowired
    private MQTTService mqttService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private LightService lightService;

    @PostMapping("/changeLightStatus")
    public ResponseEntity<?> changeLightStatus(@RequestBody LightStatus lightStatus)  {
        if(lightStatus==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
        LightLog lightLog = new LightLog();
        lightLog = new LightLog();
        lightLog.setDeviceId(lightStatus.getDeviceId());
        lightLog.setStatus(lightStatus.getStatus());
        lightLog.setLightId(lightStatus.getLightId());
        String topic = lightStatus.getDeviceId() + "/" + "light_state";
        mqttService.sendMessage(topic,lightStatus.getStatus());
        lightLog.setTimestamp(new Date());
        lightService.save(lightLog);
        return ResponseEntity.status(HttpStatus.OK).body("on/off light successfully.");
    }
}
