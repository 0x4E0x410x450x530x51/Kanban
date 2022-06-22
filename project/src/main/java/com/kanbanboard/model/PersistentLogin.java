package com.kanbanboard.model;

import javax.persistence.*;

@Entity
@Table(name = "persistent_logins")
public class PersistentLogin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "persistent_loginsID", nullable = false)
    private Integer id;

    @Column(name = "userID", nullable = false, length = 64)
    private String userID;

    @Column(name = "sessionID", nullable = false, length = 64)
    private String sessionID;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

}