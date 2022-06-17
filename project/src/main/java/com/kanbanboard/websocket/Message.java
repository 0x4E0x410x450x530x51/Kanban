package com.kanbanboard.websocket;

public class Message {
    private String from;
    private String content;



    public void setFrom(String from) {
        this.from = from;
    }
    public void setMessage(String content) {
        this.content = content;
    }

    public String getFrom() {
        return this.from;
    }

    public String getMessage() {
        return this.content;
    }


}