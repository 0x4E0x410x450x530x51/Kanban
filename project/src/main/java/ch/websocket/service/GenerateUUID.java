package ch.websocket.service;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GenerateUUID {

    public UUID generateUUID() {
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid);
        return uuid;
    }

}
