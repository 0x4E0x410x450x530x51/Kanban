package com.kanbanboard.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "story")
public class Story {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "storyID", nullable = false)
    private Integer id;

    @Column(name = "storyname")
    private String storyname;

    @Column(name = "storyStatus")
    private Integer storyStatus;

    @Column(name = "storyDescription", length = 1024)
    private String storyDescription;

    @Column(name = "storyExpire")
    private LocalDate storyExpire;

    @Column(name = "storyAssign")
    private Integer storyAssign;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStoryname() {
        return storyname;
    }

    public void setStoryname(String storyname) {
        this.storyname = storyname;
    }

    public Integer getStoryStatus() {
        return storyStatus;
    }

    public void setStoryStatus(Integer storyStatus) {
        this.storyStatus = storyStatus;
    }

    public String getStoryDescription() {
        return storyDescription;
    }

    public void setStoryDescription(String storyDescription) {
        this.storyDescription = storyDescription;
    }

    public LocalDate getStoryExpire() {
        return storyExpire;
    }

    public void setStoryExpire(LocalDate storyExpire) {
        this.storyExpire = storyExpire;
    }

    public Integer getStoryAssign() {
        return storyAssign;
    }

    public void setStoryAssign(Integer storyAssign) {
        this.storyAssign = storyAssign;
    }

}