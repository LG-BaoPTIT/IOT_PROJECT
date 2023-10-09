package com.example.spring.service;


import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MQTTService {


    @Autowired
    private MqttClient mqttClient;

    public void sendMessage(String topic, String message) {
        try {
            mqttClient.publish(topic, message.getBytes(), 0, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void subscribeToTopic(String topic) {
        try {
            mqttClient.subscribe(topic);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    // Xử lý dữ liệu khi nhận được message
    public void handleMessage(String topic, String message) {

    }
}
