package com.example.spring.controller;

import com.example.spring.payload.request.HelloMessage;
import com.example.spring.service.RealTimeDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@Controller

public class TemperatureController {
    @Autowired
    private RealTimeDataService realTimeDataService;

//    @MessageMapping("/start")
//    public void startSendingRealTimeData() {
//        realTimeDataService.sendRealTimeData();
//    }

//    @MessageMapping("/hello")
//    //@SendTo("/topic/greetings")
//    public void greeting(HelloMessage message) throws Exception {
//        Thread.sleep(1000); // simulated delay
//        realTimeDataService.sendRealTimeData();
//        //return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
//    }

}
