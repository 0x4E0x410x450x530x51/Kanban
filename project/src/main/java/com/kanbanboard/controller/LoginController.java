package com.kanbanboard.controller;



import com.kanbanboard.model.User;
import com.kanbanboard.payload.LoginRequest;
import com.kanbanboard.payload.SignUpRequest;
import com.kanbanboard.encryption.Encryption;
import com.kanbanboard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;


@Controller
@RequestMapping("/api")
public class LoginController {
    @Autowired
    public UserRepository userRepository;
    Encryption encrypt = new Encryption();

    public LoginController (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping(value = "/login")
    public Boolean login(@RequestBody LoginRequest payload) throws NoSuchAlgorithmException {

        String encodedPassword = encrypt.toHexString(encrypt.getSHA(payload.getPassword()));
        User userdata = userRepository.findByEmail(payload.getEmail());
        Boolean correctPassword = encrypt.passwordEqual(encodedPassword, userdata.getPassword());

        return correctPassword;
    }

    @PostMapping(value = "/signup")
    public String signup(@RequestBody SignUpRequest payload) throws NoSuchAlgorithmException {



        if (userRepository.existsByEmail(payload.getEmail())) {
            return "Mail in use!";
        } else {


            Encryption encryption = new Encryption();
            String encryptedPassword = encryption.toHexString(encryption.getSHA(payload.getPassword()));

            User user = new User(payload.getFullname(), payload.getEmail(), encryptedPassword);
            userRepository.save(user);
            return "Success";
        }
    }


}
