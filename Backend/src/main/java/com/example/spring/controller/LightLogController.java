package com.example.spring.controller;

import com.example.spring.payload.request.LedStatus;
import com.example.spring.service.MQTTService;
import com.example.spring.service.LightLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/led")
public class LightLogController {

    @Autowired
    private LightLogService lightLogService;

    @Autowired
    private MQTTService mqttService;

    @PostMapping("/update")
    public String controlLed(@RequestBody LedStatus ledStatus) {
        // Lưu trạng thái LED vào cơ sở dữ liệu
        try {
            lightLogService.saveLedStatus(ledStatus);

            // Xuất bản topic MQTT để bật/tắt đèn
            String topic = "led_state" ;
            String message = "/" +ledStatus.getDevice().getDeviceId() + "/" + ledStatus.getLightId() + "/" + ledStatus.getStatus();
            mqttService.sendMessage(topic, message);
            return "Message published successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error publishing message: " + e.getMessage();
        }
    }
}
