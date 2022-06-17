package com.kanbanboard.dto;

public class MessageDto {

    public MessageDto(String m, String e) {
        this.email = e;
        this.message = m;
    }

    private String message;

    private String email;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



}
