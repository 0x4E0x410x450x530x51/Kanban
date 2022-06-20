package com.kanbanboard.controller;

import com.kanbanboard.dto.MessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {

    @MessageMapping("/{id}")
    @SendTo("/kanban/{id}")
    public MessageDto getMessages(MessageDto dto){
        return dto;
    }




}
