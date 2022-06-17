package com.kanbanboard.service;

import com.kanbanboard.controller.MessageController;
import com.kanbanboard.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;

@Service
public class AuthChannelInterceptor implements ChannelInterceptor {

    private final WebSocketAuthenticatorService service;
    private static final String EMAIL_HEADER = "email";
    private static final String PASSWORD_HEADER = "password";


    @Autowired
    public AuthChannelInterceptor(WebSocketAuthenticatorService service){

        this.service = service;

    }

    // Processes a message before sending it
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        // Instantiate an object for retrieving the STOMP headers
        final StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        // Check that the object is not null
        assert accessor != null;
        // If the frame is a CONNECT frame

        if(accessor.getCommand() == StompCommand.CONNECT){

            // retrieve the username from the headers
            final String email = accessor.getFirstNativeHeader(EMAIL_HEADER);
            // retrieve the password from the headers
            final String password = accessor.getFirstNativeHeader(PASSWORD_HEADER);
            // authenticate the user and if that's successful add their user information to the headers
            UsernamePasswordAuthenticationToken user = service.getAuthenticatedOrFail(email, password);
            UserService a = service.getUS();
            Message<?> message1 = MessageBuilder.withPayload("219837123")
                    .setHeader("test1", "test2")
                    .build();
            try {
                User joiner = a.findbyEmail(email);
                message1 = MessageBuilder.withPayload(joiner.getFullname() +" has joined the channel!")
                        .setHeader("test1", "test2")
                        .build();
            } catch (UsernameNotFoundException e) {
                e.printStackTrace();
            }
            accessor.setUser(user);

        }

        return message;
    }

}
