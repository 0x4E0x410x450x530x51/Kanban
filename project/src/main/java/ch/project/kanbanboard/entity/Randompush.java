package ch.project.kanbanboard.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "randompush")
public class Randompush {
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "demandLevel", nullable = false)
    private Double demandLevel;

    @Column(name = "batchsize", nullable = false)
    private Integer batchsize;

    @Column(name = "`desc`", nullable = false)
    private String desc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getDemandLevel() {
        return demandLevel;
    }

    public void setDemandLevel(Double demandLevel) {
        this.demandLevel = demandLevel;
    }

    public Integer getBatchsize() {
        return batchsize;
    }

    public void setBatchsize(Integer batchsize) {
        this.batchsize = batchsize;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}