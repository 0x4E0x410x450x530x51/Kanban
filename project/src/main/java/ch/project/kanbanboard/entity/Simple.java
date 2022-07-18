package ch.project.kanbanboard.entity;

import javax.persistence.Entity;

public class Simple {

    public String fleetId;

    public String text;

    public Simple(String fleetId, String text) {
        this.fleetId = fleetId;
        this.text = text;
    }
}
