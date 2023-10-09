package com.example.spring.config;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.handler.GenericHandler;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.messaging.MessageChannel;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import com.example.spring.handler.MyTopicMessageHandler;
import org.springframework.messaging.support.MessageBuilder;

@Configuration
public class MQTTConfig {
    @Value("${mqtt.broker-url}")
    private String brokerUrl;

    @Value("${mqtt.username}")
    private String username;

    @Value("${mqtt.password}")
    private String password;

//
//    @Bean
//    public MessageChannel myTopicInputChannel() {
//        return new DirectChannel();
//    }
//
//    @Bean
//    public IntegrationFlow myTopicFlow() {
//        return IntegrationFlows.from(
//                        new MqttPahoMessageDrivenChannelAdapter("myClientId", mqttClientFactory(), "mytopic")
//                )
//                .channel("myTopicInputChannel")
//                .handle(new MyTopicMessageHandler())
//                .get(); // Đảm bảo kết thúc IntegrationFlow bằng .get()
//    }
//
//
//
//
//    @Bean
//    public MqttPahoClientFactory mqttClientFactory() {
//        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
//        MqttConnectOptions options = new MqttConnectOptions();
//        options.setUserName(username);
//        options.setPassword(password.toCharArray());
//        factory.setConnectionOptions(options);
//        return factory;
//    }


    @Bean
    public MqttClient mqttClient() {
        try {
            MqttClient mqttClient = new MqttClient(brokerUrl, MqttClient.generateClientId());
            MqttConnectOptions options = new MqttConnectOptions();
            options.setUserName(username);
            options.setPassword(password.toCharArray());
            mqttClient.connect(options);
            System.out.println("Connected to HiveMQ successfully!");
            return mqttClient;
        } catch (Exception e) {
            throw new RuntimeException("Failed to create MQTT client", e);
        }
    }
}
