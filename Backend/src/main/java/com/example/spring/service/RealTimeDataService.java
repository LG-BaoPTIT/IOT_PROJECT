package com.example.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RealTimeDataService {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public void sendRealTimeData(){
        double data = Math.random();

        messagingTemplate.convertAndSend("/topic", data);
    }
}
