package com.kanbanboard.service;

import com.kanbanboard.dto.UserDto;
import com.kanbanboard.model.User;
import com.kanbanboard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepo, PasswordEncoder passwordEncoder){

        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;

    }

    public User findbyEmail(String email){

        return userRepo.findByEmail(email);

    }

    public void saveUser(UserDto dto){

        userRepo.save(new User(dto, passwordEncoder));

    }

}
