package com.kanbanboard.model;

import javax.persistence.*;

@Entity
@Table(name = "kanbanboard_story")
public class KanbanboardStory {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kanbanboardID")
    private Kanbanboard kanbanboardID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "storyID")
    private Story storyID;

    public Kanbanboard getKanbanboardID() {
        return kanbanboardID;
    }

    public void setKanbanboardID(Kanbanboard kanbanboardID) {
        this.kanbanboardID = kanbanboardID;
    }

    public Story getStoryID() {
        return storyID;
    }

    public void setStoryID(Story storyID) {
        this.storyID = storyID;
    }

}