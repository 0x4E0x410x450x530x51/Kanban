package com.kanbanboard.application;

import com.kanbanboard.controller.LoginController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.kanbanboard.controller"})

public class KanbansimApplication {

    public static void main(String[] args) {
        SpringApplication.run(KanbansimApplication.class, args);
    }

}
