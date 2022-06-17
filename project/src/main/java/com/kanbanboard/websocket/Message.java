package com.kanbanboard.websocket;

public class Message {
    private String from;
    private String content;



    public void setFrom(String from) {
        this.from = from;
    }


    public String getFrom() {
        return this.from;
    }
  
    public String getMessage() {
      return this.content;
    }
    public void setMessage(String m) {
      this.content = m;
    }



}