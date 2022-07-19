package ch.project.kanbanboard.entity;

import javax.persistence.*;

@Entity
@Table(name = "departmentsized")
public class Departmentsized {
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sizedSettings", nullable = false)
    private Sizedsetting sizedSettings;

    @Column(name = "effort", nullable = false)
    private Integer effort;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "departmentID", nullable = false)
    private Department departmentID;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Sizedsetting getSizedSettings() {
        return sizedSettings;
    }

    public void setSizedSettings(Sizedsetting sizedSettings) {
        this.sizedSettings = sizedSettings;
    }

    public Integer getEffort() {
        return effort;
    }

    public void setEffort(Integer effort) {
        this.effort = effort;
    }

    public Department getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(Department departmentID) {
        this.departmentID = departmentID;
    }

}