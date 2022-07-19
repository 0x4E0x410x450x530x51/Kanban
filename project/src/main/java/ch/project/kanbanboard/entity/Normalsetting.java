package ch.project.kanbanboard.entity;

import javax.persistence.*;

@Entity
@Table(name = "normalsettings")
public class Normalsetting {
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "meanHours", nullable = false)
    private Double meanHours;

    @Column(name = "variations", nullable = false)
    private Double variations;

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

    public Double getMeanHours() {
        return meanHours;
    }

    public void setMeanHours(Double meanHours) {
        this.meanHours = meanHours;
    }

    public Double getVariations() {
        return variations;
    }

    public void setVariations(Double variations) {
        this.variations = variations;
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