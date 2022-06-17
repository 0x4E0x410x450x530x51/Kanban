package com.kanbanboard.model;

import com.kanbanboard.dto.UserDto;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userID", nullable = false)
    private Integer id;

    @Column(name = "username")
    @NotBlank
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    @NotBlank
    private String password;

    @Column(name = "authority")
    private String authority;

    private String fullname;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }


    public User(){



    }

    public User(UserDto dto, PasswordEncoder encoder){

        email = dto.getEmail();
        password = encoder.encode(dto.getPassword());
        authority = "ROLE_USER";

    }

    public User(String Fullname, String  Email, String password){
        email = Email;
        password = password;
        authority = "ROLE_USER";
        fullname = Fullname;

    }




}

