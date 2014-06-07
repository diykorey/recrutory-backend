package com.kandidato.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@javax.persistence.Entity
@Table(name = "FLOWS")
public class Flow implements Entity, CreatorAware {

    @Id
    @Column(name = "FLOW_ID")
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CANDIDATE_ID")
    private Candidate candidate;

    @ManyToOne
    @JoinColumn(name = "VACANCY_ID")
    private Vacancy vacancy;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "FLOW_ID")
    @JsonIgnore
    private List<FlowAction> actions = new ArrayList<>();

    @Column(name = "CREATION_TIME")
    private Date createTime;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User creator;

    //TODO Do we need active flag here? As for me it overlaps with the FlowState of the last action in the current flow.
    @Column(name = "ACTIVE_FLAG")
    private boolean active = true;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
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
    public User getCreator() {
        return this.creator;
    }

    @Override
    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Flow() {

    }

    public Flow(Vacancy vacancy, Candidate candidate) {
        this.vacancy = vacancy;
        this.candidate = candidate;
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
