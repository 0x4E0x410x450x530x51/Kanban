package ch.project.kanbanboard.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "scrumcreate")
public class Scrumcreate {
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "iteration_len", nullable = false)
    private Integer iterationLen;

    @Column(name = "taskPerIteration", nullable = false)
    private Integer taskPerIteration;

    @Column(name = "wipSub", nullable = false)
    private Boolean wipSub = false;

    @Column(name = "`desc`", nullable = false)
    private String desc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIterationLen() {
        return iterationLen;
    }

    public void setIterationLen(Integer iterationLen) {
        this.iterationLen = iterationLen;
    }

    public Integer getTaskPerIteration() {
        return taskPerIteration;
    }

    public void setTaskPerIteration(Integer taskPerIteration) {
        this.taskPerIteration = taskPerIteration;
    }

    public Boolean getWipSub() {
        return wipSub;
    }

    public void setWipSub(Boolean wipSub) {
        this.wipSub = wipSub;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}