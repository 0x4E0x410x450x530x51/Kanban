package ch.project.kanbanboard.entity;

import javax.persistence.*;

@Entity
@Table(name = "createstrat")
public class Createstrat {
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID", nullable = false)
    private Settingsconfiguration settingsconfiguration;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "constantpushID", nullable = false)
    private Constantpush constantpushID;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "randompushID", nullable = false)
    private Randompush randompushID;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "scrumcreateID", nullable = false)
    private Scrumcreate scrumcreateID;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Settingsconfiguration getSettingsconfiguration() {
        return settingsconfiguration;
    }

    public void setSettingsconfiguration(Settingsconfiguration settingsconfiguration) {
        this.settingsconfiguration = settingsconfiguration;
    }

    public Constantpush getConstantpushID() {
        return constantpushID;
    }

    public void setConstantpushID(Constantpush constantpushID) {
        this.constantpushID = constantpushID;
    }

    public Randompush getRandompushID() {
        return randompushID;
    }

    public void setRandompushID(Randompush randompushID) {
        this.randompushID = randompushID;
    }

    public Scrumcreate getScrumcreateID() {
        return scrumcreateID;
    }

    public void setScrumcreateID(Scrumcreate scrumcreateID) {
        this.scrumcreateID = scrumcreateID;
    }

}