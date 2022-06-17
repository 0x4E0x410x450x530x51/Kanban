package com.kanbanboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication(scanBasePackages = "com.kanbanboard")
public class KanbansimApplication {

    public static void main(String[] args) {

        BCryptPasswordEncoder bc = new BCryptPasswordEncoder(10);
        System.out.println(bc.encode("12345"));

        SpringApplication.run(KanbansimApplication.class, args);
        
    }

}
