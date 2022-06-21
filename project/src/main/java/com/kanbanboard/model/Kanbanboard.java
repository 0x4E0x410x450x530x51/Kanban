package com.kanbanboard.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "kanbanboard")
public class Kanbanboard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kanbanboardID", nullable = false)
    private Integer id;

    @Column(name = "created")
    private LocalDate created;

    @Column(name = "completed")
    private Integer completed;

    @Column(name = "inprogress")
    private Integer inprogress;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public Integer getCompleted() {
        return completed;
    }

    public void setCompleted(Integer completed) {
        this.completed = completed;
    }

    public Integer getInprogress() {
        return inprogress;
    }

    public void setInprogress(Integer inprogress) {
        this.inprogress = inprogress;
    }

}