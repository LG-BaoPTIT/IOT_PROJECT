package com.example.spring.config;

import com.example.spring.payload.response.Greeting;
import com.example.spring.service.RealTimeDataService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.handler.GenericHandler;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.integration.support.MessagingExceptionWrapper;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.util.HtmlUtils;


@Configuration
public class MQTTConfig {
    @Value("${mqtt.broker-url}")
    private String brokerUrl;

    @Value("${mqtt.username}")
    private String username;

    @Value("${mqtt.password}")
    private String password;


    @Autowired
    private RealTimeDataService realTimeDataService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Bean
    public MqttPahoClientFactory mqttClientFactory() {

        try {
            DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
            MqttConnectOptions options = new MqttConnectOptions();
            options.setServerURIs(new String[] {brokerUrl});
            options.setUserName(username);
            String pass = password;
            options.setPassword(pass.toCharArray());
            options.setCleanSession(true);

            factory.setConnectionOptions(options);
            System.out.println("Connected to HiveMQ successfully!");
            return factory;
        } catch (Exception e) {
            throw new RuntimeException("Failed to create MQTT client", e);
        }
    }

    @Bean
    public MessageChannel mqttInputChannel()  {
        return new DirectChannel();
    }

    @Bean
    public MessageProducer inbound() {
        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter("serverIn", mqttClientFactory(), "#");
        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(2);
        adapter.setOutputChannel(mqttInputChannel());
        return adapter;
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public MessageHandler handler() {
        return new MessageHandler() {
            @Override
            public void handleMessage(Message<?> message) throws MessagingExceptionWrapper {
                String topic = message.getHeaders().get(MqttHeaders.RECEIVED_TOPIC).toString();
                if(topic.equals("esp8266_data")) {
                    String payload = message.getPayload().toString();
                    //                        ObjectMapper objectMapper = new ObjectMapper();
//                        DataSensorDTO dataSensorDTO = objectMapper.readValue(payload, DataSensorDTO.class);
//                        dataSensorRepository.save(dataSensorConverter.toEntity(dataSensorDTO));
                    //                    sseService.sendToAll(payload);
//                    chartSSEService.sendToAll();
                   Greeting a = new Greeting(message.getPayload() + HtmlUtils.htmlEscape("t") + "!");
                    messagingTemplate.convertAndSend("/topic/greetings", a);
                    System.out.println(message.getPayload());
                }
            }
        };
    }
    @Bean
    public MessageChannel mqttOutboundChannel() {
        return new DirectChannel();
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttOutboundChannel")
    public MessageHandler mqttOutbound() {
        MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler("serverOut", mqttClientFactory());
        messageHandler.setAsync(true);
        messageHandler.setDefaultTopic("#");
        messageHandler.setDefaultRetained(false);
        return messageHandler;
    }
}
