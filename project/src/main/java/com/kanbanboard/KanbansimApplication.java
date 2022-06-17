package com.kanbanboard;

import com.kanbanboard.websocket.WebSocketServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.kanbanboard"})

public class KanbansimApplication{

    public static void main(String[] args) {
        SpringApplication.run(KanbansimApplication.class, args);

        
    }
    


}
