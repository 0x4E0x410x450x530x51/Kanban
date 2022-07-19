package ch.project.kanbanboard.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rules")
public class Rule {
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "maxWipPerPerson", nullable = false)
    private Integer maxWipPerPerson;

    @Column(name = "maxPeopleWorkOnWip", nullable = false)
    private Integer maxPeopleWorkOnWip;

    @Column(name = "warmupTime", nullable = false)
    private Double warmupTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMaxWipPerPerson() {
        return maxWipPerPerson;
    }

    public void setMaxWipPerPerson(Integer maxWipPerPerson) {
        this.maxWipPerPerson = maxWipPerPerson;
    }

    public Integer getMaxPeopleWorkOnWip() {
        return maxPeopleWorkOnWip;
    }

    public void setMaxPeopleWorkOnWip(Integer maxPeopleWorkOnWip) {
        this.maxPeopleWorkOnWip = maxPeopleWorkOnWip;
    }

    public Double getWarmupTime() {
        return warmupTime;
    }

    public void setWarmupTime(Double warmupTime) {
        this.warmupTime = warmupTime;
    }

}