package com.example.spring.handler;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Service;
import org.springframework.integration.annotation.ServiceActivator;

//@Service
public class MyTopicMessageHandler {

    // @ServiceActivator(inputChannel = "myTopicInputChannel")
    public void handleMyTopicMessage(String payload) {
        // Xử lý dữ liệu từ message của "mytopic" ở đây
        System.out.println("Received message from mytopic: " + payload);
    }
}
