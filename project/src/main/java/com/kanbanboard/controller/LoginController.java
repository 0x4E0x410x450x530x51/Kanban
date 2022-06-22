package com.kanbanboard.controller;



import com.kanbanboard.config.AppSecurityConfig;
import com.kanbanboard.model.PersistentLogin;
import com.kanbanboard.model.User;
import com.kanbanboard.payload.LoginRequest;
import com.kanbanboard.payload.SignUpRequest;
import com.kanbanboard.encryption.Encryption;
import com.kanbanboard.repository.PersistentLoginRepository;
import com.kanbanboard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.http.HttpRequest;
import java.util.Collections;


@RestController
@RequestMapping("/api")
public class LoginController {
    @Autowired
    public PersistentLoginRepository persistentLoginRepository;
    @Autowired
    public UserRepository userRepository;
    Encryption encrypt = new Encryption();
    PersistentLogin persistentLogin = new PersistentLogin();
    AppSecurityConfig appSecurityConfig = new AppSecurityConfig();
    public LoginController (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping(value = "/login")
    public String login(@RequestBody LoginRequest payload, HttpServletResponse response, HttpServletRequest request) {


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




        if (correctPassword) {

            System.out.println(request.getSession().getId());

            if (!persistentLoginRepository.existsByUserID(userdata.getId().toString())) {
                createCookie(response, request, userdata);
            } else if(!persistentLoginRepository.existsBySessionID(request.getSession().getId())){
                PersistentLogin p = persistentLoginRepository.findByUserID(userdata.getId().toString());
                persistentLoginRepository.deleteAllById(Collections.singleton((Integer) p.getId()));
                createCookie(response, request, userdata);

            }





            // if user already has session in database, retrieve and assign. Else create new and add db entry....
            return "Success!";
        }
        return "Incorrect credentials";
    }

    private void createCookie(HttpServletResponse response, HttpServletRequest request, User userdata) {
        Cookie cookie = new Cookie("KSESSION", request.getSession().getId());
        cookie.setSecure(true);
        response.addCookie(cookie);
        persistentLogin.setSessionID(request.getSession().getId());
        persistentLogin.setUserID(userdata.getId().toString());
        persistentLoginRepository.save(persistentLogin);
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
