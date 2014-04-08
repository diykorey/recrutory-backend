package com.kandidato.persistence.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@javax.persistence.Entity
@Table(name = "FLOWS")
public class Flow implements Entity {

    @Id
    @Column(name = "FLOW_ID")
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "PERSON_ID")
    private Person person;

    @ManyToOne
    @JoinColumn(name = "VACANCY_ID")
    private Vacancy vacancy;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "FLOW_ID")
    private List<FlowAction> actions = new ArrayList<>();

    @Column(name = "CREATION_TIME")
    private Date createTime;

    //TODO Do we need active flag here? As for me it overlaps with the FlowState of the last action in the current flow.
    @Column(name = "ACTIVE_FLAG")
    private boolean active = true;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Vacancy getVacancy() {
        return vacancy;
    }

    public void setVacancy(Vacancy vacancy) {
        this.vacancy = vacancy;
    }

    public List<FlowAction> getActions() {
        return actions;
    }

    public void setActions(List<FlowAction> actions) {
        this.actions = actions;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(this.getClass().getSimpleName());
        builder.append(": {");
        builder.append("id: ");
        builder.append(id);
        builder.append(", createTime: ");
        builder.append(createTime);
        builder.append(", active: ");
        builder.append(active);
        builder.append("}");
        return builder.toString();
    }
}
