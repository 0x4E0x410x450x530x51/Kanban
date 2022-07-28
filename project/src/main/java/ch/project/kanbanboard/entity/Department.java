package ch.project.kanbanboard.entity;

import javax.persistence.*;

@Entity
@Table(name = "departments")
public class Department {
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "settingsID", nullable = false)
    private Settingsconfiguration settingsID;

    @Column(name = "departmentName", nullable = false)
    private String departmentName;

    @Column(name = "members", nullable = false)
    private Integer members;

    @Column(name = "colIndex", nullable = false)
    private Integer colIndex;

    @Column(name = "efficency", nullable = false)
    private Integer efficency;

    @Column(name = "doingLimit", nullable = false)
    private Integer doingLimit;

    @Column(name = "doneLimit", nullable = false)
    private Integer doneLimit;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Settingsconfiguration getSettingsID() {
        return settingsID;
    }

    public void setSettingsID(Settingsconfiguration settingsID) {
        this.settingsID = settingsID;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Integer getMembers() {
        return members;
    }

    public void setMembers(Integer members) {
        this.members = members;
    }

    public Integer getColIndex() {
        return colIndex;
    }

    public void setColIndex(Integer colIndex) {
        this.colIndex = colIndex;
    }

    public Integer getEfficency() {
        return efficency;
    }

    public void setEfficency(Integer efficency) {
        this.efficency = efficency;
    }

    public Integer getDoingLimit() {
        return doingLimit;
    }

    public void setDoingLimit(Integer doingLimit) {
        this.doingLimit = doingLimit;
    }

    public Integer getDoneLimit() {
        return doneLimit;
    }

    public void setDoneLimit(Integer doneLimit) {
        this.doneLimit = doneLimit;
    }

}