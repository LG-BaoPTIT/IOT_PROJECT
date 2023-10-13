package com.example.spring.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;
import org.springframework.security.config.annotation.web.socket.EnableWebSocketSecurity;

@Configuration
@EnableWebSocketSecurity
public class WebSocketSecurityConfig extends AbstractSecurityWebSocketMessageBrokerConfigurer {

    @Override
    protected void configureInbound(MessageSecurityMetadataSourceRegistry messages) {
        messages
                .simpDestMatchers("/topic/**","/start","/ws","/ws/**","/ws").permitAll() // Cho phép truy cập các đường dẫn WebSocket bắt đầu bằng "/topic/"
                .anyMessage().authenticated(); // Yêu cầu xác thực cho tất cả các tin nhắn WebSocket khác
    }

    @Override
    protected boolean sameOriginDisabled() {
        return true; // Vô hiệu hóa kiểm tra chính xác gốc (CORS) cho WebSocket
    }
}

