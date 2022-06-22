package com.kanbanboard.config;

import com.kanbanboard.service.AuthChannelInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {

    @Autowired
    private AuthChannelInterceptor channelInterceptor;

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {

        // Set prefixes for the endpoint that the client listens for our messages from
        registry.enableSimpleBroker("/kanban");
        // Set prefix for endpoints the client will send messages to
        registry.setApplicationDestinationPrefixes("/boardSend");

    }


    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {

        // Registers the endpoint where the handshake will take place
        registry.addEndpoint("/board")
                // Allow the origin http://localhost:63343 to send messages to us. (Base url of the client)
                .setAllowedOrigins("http://localhost:8080")
                // Enable SockJS fallback options
                .withSockJS();
        registry.addEndpoint("/kanban/**").withSockJS();

    }
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {

        // Add our interceptor for authentication/authorization
        registration.interceptors(channelInterceptor);

    }
}
