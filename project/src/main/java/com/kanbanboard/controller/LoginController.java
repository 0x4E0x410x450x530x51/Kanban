package com.kanbanboard.controller;



import com.kanbanboard.config.AppSecurityConfig;
import com.kanbanboard.model.User;
import com.kanbanboard.payload.LoginRequest;
import com.kanbanboard.payload.SignUpRequest;
import com.kanbanboard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/api")
public class LoginController {
    @Autowired
    public UserRepository userRepository;
    AppSecurityConfig appSecurityConfig = new AppSecurityConfig();
    public LoginController (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping(value = "/login")
    public String login(@RequestBody LoginRequest payload, HttpServletRequest req) {

        if (payload.getEmail().isEmpty() || payload.getPassword().isEmpty()) {
            return "Enter all values!";
        }

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

                // if user already has session in database, retrieve and assign. Else create new and add db entry....
                //req.getSession().setAttribute("KANBANSESSIONID", sessId);
            System.out.println("Success");
                return "Success!";




        }
        //Activsession activsession = activsessionRepository.findBySessionID(sessId);


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
