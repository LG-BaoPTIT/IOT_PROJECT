package com.example.spring.controller;

import com.example.spring.entity.LightLog;
import com.example.spring.payload.request.LightStatus;
import com.example.spring.service.LightService;
import com.example.spring.service.MQTTService;
import com.example.spring.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Autowired
    private UserService userService;

    @PostMapping("/changeLightStatus")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> changeLightStatus(@RequestBody LightStatus lightStatus)  {
        if(lightStatus==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        System.out.println("Người dùng " + userName + " gửi yêu cầu đến URI /changeLightStatus" );
        LightLog lightLog =new LightLog();
        lightLog.setLightId(lightStatus.getLightId());
        lightLog.setTimestamp(new Date());
        lightLog.setStatus(lightStatus.getStatus());
        String topic =userService.getDeviceIdByUserName(userName) + "/" + lightStatus.getLightId() + "/" + "light_state";
        mqttService.sendMessage(topic,lightStatus.getStatus());
        System.out.println(topic);
        lightLog.setTimestamp(new Date());
        lightService.save(lightLog);
        return ResponseEntity.status(HttpStatus.OK).body("on/off light successfully.");
    }
}
