package com.kanbanboard.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_kanbanboard")
public class UserKanbanboard implements Serializable {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kanbanboardID")

    private Kanbanboard kanbanboardID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userID")
    private User userID;

    @Column(name = "owner")
    private Boolean owner;

    public Kanbanboard getKanbanboardID() {
        return kanbanboardID;
    }

    public void setKanbanboardID(Kanbanboard kanbanboardID) {
        this.kanbanboardID = kanbanboardID;
    }

    public User getUserID() {
        return userID;
    }

    public void setUserID(User userID) {
        this.userID = userID;
    }

    public Boolean getOwner() {
        return owner;
    }

    public void setOwner(Boolean owner) {
        this.owner = owner;
    }

}