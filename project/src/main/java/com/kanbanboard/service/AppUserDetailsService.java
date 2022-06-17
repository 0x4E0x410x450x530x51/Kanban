package com.kanbanboard.service;

import com.kanbanboard.dto.AppUserDetails;
import com.kanbanboard.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public AppUserDetailsService(UserService userService){

        this.userService = userService;

    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user = userService.findbyEmail(s);
        if(user == null){

            throw new UsernameNotFoundException("User does not exist");

        }




        return new AppUserDetails(user);

    }
}
