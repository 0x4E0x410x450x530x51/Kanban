package ch.project.kanbanboard.entity;

import javax.persistence.*;

@Entity
@Table(name = "constantsettings")
public class Constantsetting {
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "amountHours", nullable = false)
    private Double amountHours;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "departmentID", nullable = false)
    private Department departmentID;

    @Column(name = "`desc`", nullable = false)
    private String desc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getAmountHours() {
        return amountHours;
    }

    public void setAmountHours(Double amountHours) {
        this.amountHours = amountHours;
    }

    public Department getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(Department departmentID) {
        this.departmentID = departmentID;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}