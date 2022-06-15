package com.kanbanboard.controller;



import com.kanbanboard.Payload.LoginRequest;
import com.kanbanboard.encryption.Encryption;
import com.kanbanboard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.NoSuchAlgorithmException;


@Controller
@ResponseBody
@RequestMapping("/api")
public class LoginController {

    @Autowired
    UserRepository userRepository;

    @GetMapping(value = "/login")
    public String login(LoginRequest payload) throws NoSuchAlgorithmException {

        Encryption encrypt = new Encryption();



        String password = payload.getPassword();

        byte[] passwordByte = encrypt.getSHA(password);
        
        String encodedPassword = encrypt.toHexString(passwordByte);

        userRepository.findByEmail();





        return "Test";
    }


}
