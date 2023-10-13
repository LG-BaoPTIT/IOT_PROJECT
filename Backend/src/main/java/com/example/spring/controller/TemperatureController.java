package com.example.spring.controller;

import com.example.spring.service.RealTimeDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class TemperatureController {
    @Autowired
    private RealTimeDataService realTimeDataService;

    @MessageMapping("/start")
    public void startSendingRealTimeData() {
        realTimeDataService.sendRealTimeData();
    }
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public String greeting(String message) throws Exception {
        System.out.println(message);
        Thread.sleep(1000); // simulated delay
        return message;
    }
}
