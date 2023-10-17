package com.example.spring.service;

import com.example.spring.config.WebSocketEventListener;
import com.example.spring.payload.response.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

@Service
public class RealTimeDataService {

    @Autowired
    private WebSocketEventListener webSocketEventListener;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

   // @Scheduled(fixedRate = 1000)
    public void sendRealTimeData() throws InterruptedException {
        double data = Math.random();
//        for (int i=0;i<=100;i++){
        if (webSocketEventListener.isConnected()) {
            String t = data + "";
            Greeting a = new Greeting("Hello, " + HtmlUtils.htmlEscape(t) + "!");
            messagingTemplate.convertAndSend("/topic/greetings", a);
            System.out.println("t");
            Thread.sleep(1000); // simulated delay
        }
      //  }

    }
}
