package com.kanbanboard.websocket;

import com.kanbanboard.repository.UserKanbanboardRepository;
import com.kanbanboard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
public class WebSecurity {

    @Autowired
    UserKanbanboardRepository userKanbanboardRepository;

    @Autowired
    UserRepository userRepository;
    @Bean
    public boolean checkUserId(Authentication authentication, int id) {
        /*
        System.out.println(id);

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            System.out.println(currentUserName);
        }
        //List allKanbanboards = userKanbanboardRepository.findByKanbanboardID_Id(id);


        return true;
        */
        return true;
    }
}
