package com.example.spring.config;

import com.amazonaws.services.iot.client.AWSIotException;
import com.amazonaws.services.iot.client.AWSIotMqttClient;
import com.amazonaws.services.iot.client.AWSIotQos;
import com.example.spring.dto.IOTPayload;
import com.example.spring.dto.MyMessage;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQTTConfig {
    String clientEndpoint = "a1lwysb0m3hyfr-ats.iot.us-east-2.amazonaws.com";   // use value returned by describe-endpoint --endpoint-type "iot:Data-ATS"
    String clientId = "ModuleESP8266";                              // replace with your own client ID. Use unique client IDs for concurrent connections.
    String awsAccessKeyId ="";
    String awsSecretAccessKey ="";
    String sessionToken =null;
    AWSIotMqttClient client = null;

    public void connectAwsIot() {
        // AWS IAM credentials could be retrieved from AWS Cognito, STS, or other secure sources
        client = new AWSIotMqttClient(clientEndpoint, clientId, awsAccessKeyId, awsSecretAccessKey, sessionToken);
        // optional parameters can be set before connect()
        try {
            client.connect();
            System.out.println("Connected to AWS IOT");
        } catch (AWSIotException e) {
            throw new RuntimeException(e);
        }
    }

    public void publish(IOTPayload payload){
        String topic = "my/own/topic";
        AWSIotQos qos = AWSIotQos.QOS0;
        long timeout = 3000;                    // milliseconds
        MyMessage message = new MyMessage(topic, qos, "hjesdfsdf");
        try {
            client.publish(message, timeout);
        } catch (AWSIotException e) {
            throw new RuntimeException(e);
        }
    }
}
