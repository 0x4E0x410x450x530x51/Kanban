package com.kanbanboard.service;

import com.kanbanboard.dto.MessageDto;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/*
    To whomever has to maintain this next year, if you're an apprentice: I'm so sorry and I hope you don't hold a grudge or lose your mind.
    If you sincerely want to fix/maintain this then I suggest reading a lot about websockets and spring-websockets. Many thanks and good luck!
*/

@Component
public class WebSocketSessionListener
{
    private List<String> connectedClientId = new ArrayList<String>();

    @EventListener
    public void connectionEstablished(SessionConnectedEvent sce) {
        MessageHeaders msgHeaders = sce.getMessage().getHeaders();
        Principal princ = (Principal) msgHeaders.get("simpUser");
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(sce.getMessage());
        List<String> nativeHeaders = sha.getNativeHeader("userId");
        if( nativeHeaders != null )
        {
            String userId = nativeHeaders.get(0);
            connectedClientId.add(userId);
            System.out.println(userId);
            //propagateDvcMsg(new MessageDto(userId+" has joined the channel!", "SYSTEM"));
        }
        else
        {
            String userId = princ.getName();
            connectedClientId.add(userId);
            System.out.println(userId);
            //propagateDvcMsg(new MessageDto(userId+" has joined the channel!", "SYSTEM"));

        }
    }

    @EventListener
    public void webSockectDisconnect(SessionDisconnectEvent sde) {
        MessageHeaders msgHeaders = sde.getMessage().getHeaders();
        Principal princ = (Principal) msgHeaders.get("simpUser");
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(sde.getMessage());
        List<String> nativeHeaders = sha.getNativeHeader("userId");
        if( nativeHeaders != null )
        {
            String userId = nativeHeaders.get(0);
            connectedClientId.remove(userId);
            System.out.println(userId);
            //propagateDvcMsg(new MessageDto(userId+" has left the channel!", "SYSTEM"));

        }
        else
        {
            String userId = princ.getName();
            connectedClientId.remove(userId);
            System.out.println(userId);
            //propagateDvcMsg(new MessageDto(userId+" has left the channel!", "SYSTEM"));


        }
    }

    public List<String> getConnectedClientId()
    {
        return connectedClientId;
    }
    public void setConnectedClientId(List<String> connectedClientId)
    {
        this.connectedClientId = connectedClientId;
    }


    @Autowired
    private SimpMessagingTemplate smp;

    public void propagateDvcMsg(MessageDto dt) {
        this.smp.convertAndSend("/topic/messages", dt);
    }


}