package com.kanbanboard;

import com.kanbanboard.websocket.WebSocketServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.kanbanboard"})

public class KanbansimApplication extends Thread{

    public static void main(String[] args) {
        WebSocketServer websocketthread = new WebSocketServer();
        SpringApplication.run(KanbansimApplication.class, args);
        websocketthread.start();
        
    }
    


}
