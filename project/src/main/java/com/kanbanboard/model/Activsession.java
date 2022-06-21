package com.kanbanboard.model;

import javax.persistence.*;

@Entity
@Table(name = "activsessions")
public class Activsession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "activSessionID", nullable = false)
    private Integer id;

    @Column(name = "sessionID")
    private Integer sessionID;

    @Column(name = "userID")
    private Integer userID;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSessionID() {
        return sessionID;
    }

    public void setSessionID(Integer sessionID) {
        this.sessionID = sessionID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

}