package ch.project.kanbanboard.entity;

import javax.persistence.*;

@Entity
@Table(name = "settingsconfiguration")
public class Settingsconfiguration {
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "boardUUID", nullable = false)
    private Board boardUUID;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "constantID", nullable = false)
    private Constantsetting constantID;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "normalID", nullable = false)
    private Normalsetting normalID;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sizedID", nullable = false)
    private Sizedsetting sizedID;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "rulesID", nullable = false)
    private Rule rulesID;

    @Column(name = "defaultValue", nullable = false)
    private Boolean defaultValue = false;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Board getBoardUUID() {
        return boardUUID;
    }

    public void setBoardUUID(Board boardUUID) {
        this.boardUUID = boardUUID;
    }

    public Constantsetting getConstantID() {
        return constantID;
    }

    public void setConstantID(Constantsetting constantID) {
        this.constantID = constantID;
    }

    public Normalsetting getNormalID() {
        return normalID;
    }

    public void setNormalID(Normalsetting normalID) {
        this.normalID = normalID;
    }

    public Sizedsetting getSizedID() {
        return sizedID;
    }

    public void setSizedID(Sizedsetting sizedID) {
        this.sizedID = sizedID;
    }

    public Rule getRulesID() {
        return rulesID;
    }

    public void setRulesID(Rule rulesID) {
        this.rulesID = rulesID;
    }

    public Boolean getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(Boolean defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}