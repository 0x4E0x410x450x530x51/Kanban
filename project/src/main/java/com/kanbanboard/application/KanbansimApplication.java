package com.kanbanboard.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class KanbansimApplication {

    public static void main(String[] args) {
<<<<<<< Updated upstream:project/src/main/java/com/kanbanboard/application/KanbansimApplication.java
        SpringApplication.run(KanbansimApplication.class, args);
=======

        BCryptPasswordEncoder bc = new BCryptPasswordEncoder(10);
        System.out.println(bc.encode("12345"));

        SpringApplication.run(KanbansimApplication.class, args);
        
>>>>>>> Stashed changes:project/src/main/java/com/kanbanboard/KanbansimApplication.java
    }

}
