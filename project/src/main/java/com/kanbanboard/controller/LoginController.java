package com.kanbanboard.controller;



import com.kanbanboard.config.AppSecurityConfig;
import com.kanbanboard.model.User;
import com.kanbanboard.payload.LoginRequest;
import com.kanbanboard.payload.SignUpRequest;
import com.kanbanboard.encryption.Encryption;
import com.kanbanboard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class LoginController {
    @Autowired
    public UserRepository userRepository;
    Encryption encrypt = new Encryption();
    AppSecurityConfig appSecurityConfig = new AppSecurityConfig();
    public LoginController (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping(value = "/login")
    public String login(@RequestBody LoginRequest payload, HttpServletRequest req) {


        // prevent non-ubs employees
        if (!payload.getEmail().split("@")[1].equals("ubs.com"))
            return "Email is not @ubs email.";

        // prevent sql injection
        if (payload.getEmail().contains("'") || payload.getEmail().contains("#"))
            return "Email contains invalid characters";

        if (!userRepository.existsByEmail(payload.getEmail()))
            return "Email does not exist";


        User userdata = userRepository.findByEmail(payload.getEmail());
        boolean correctPassword = appSecurityConfig.passwordEncoder().matches(payload.getPassword(), userdata.getPassword());


        String sessId = appSecurityConfig.passwordEncoder().encode(payload.getEmail());



        if (correctPassword) {
            // if user already has session in database, retrieve and assign. Else create new and add db entry....
            req.getSession().setAttribute("KANBANSESS", sessId);
            return "Success!";
        }
        return "Incorrect credentials";
    }

    @PostMapping(value = "/signup")
    public String signup(@RequestBody SignUpRequest payload) {



        if (userRepository.existsByEmail(payload.getEmail()) && payload.getEmail().contains("@ubs.com")) {
            return "Mail in use!";
        } else {

            String encryptedPassword = appSecurityConfig.passwordEncoder().encode(payload.getPassword());

            User user = new User(payload.getFullname(), payload.getEmail(), encryptedPassword);
            userRepository.save(user);
            return "Success";
        }
    }


}
