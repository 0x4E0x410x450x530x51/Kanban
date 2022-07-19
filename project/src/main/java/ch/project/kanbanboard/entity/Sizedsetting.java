package ch.project.kanbanboard.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sizedsettings")
public class Sizedsetting {
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "effortSmall", nullable = false)
    private Double effortSmall;

    @Column(name = "probabilitySmall", nullable = false)
    private Integer probabilitySmall;

    @Column(name = "effortMedium", nullable = false)
    private Double effortMedium;

    @Column(name = "probabilityMedium", nullable = false)
    private Integer probabilityMedium;

    @Column(name = "effortLarge", nullable = false)
    private Double effortLarge;

    @Column(name = "probabilityLarge", nullable = false)
    private Integer probabilityLarge;

    @Column(name = "effortXLarge", nullable = false)
    private Double effortXLarge;

    @Column(name = "probabilityXLarge", nullable = false)
    private Integer probabilityXLarge;

    @Column(name = "`desc`", nullable = false)
    private String desc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getEffortSmall() {
        return effortSmall;
    }

    public void setEffortSmall(Double effortSmall) {
        this.effortSmall = effortSmall;
    }

    public Integer getProbabilitySmall() {
        return probabilitySmall;
    }

    public void setProbabilitySmall(Integer probabilitySmall) {
        this.probabilitySmall = probabilitySmall;
    }

    public Double getEffortMedium() {
        return effortMedium;
    }

    public void setEffortMedium(Double effortMedium) {
        this.effortMedium = effortMedium;
    }

    public Integer getProbabilityMedium() {
        return probabilityMedium;
    }

    public void setProbabilityMedium(Integer probabilityMedium) {
        this.probabilityMedium = probabilityMedium;
    }

    public Double getEffortLarge() {
        return effortLarge;
    }

    public void setEffortLarge(Double effortLarge) {
        this.effortLarge = effortLarge;
    }

    public Integer getProbabilityLarge() {
        return probabilityLarge;
    }

    public void setProbabilityLarge(Integer probabilityLarge) {
        this.probabilityLarge = probabilityLarge;
    }

    public Double getEffortXLarge() {
        return effortXLarge;
    }

    public void setEffortXLarge(Double effortXLarge) {
        this.effortXLarge = effortXLarge;
    }

    public Integer getProbabilityXLarge() {
        return probabilityXLarge;
    }

    public void setProbabilityXLarge(Integer probabilityXLarge) {
        this.probabilityXLarge = probabilityXLarge;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}