package com.kandidato.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kandidato.constants.VacancyState;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@javax.persistence.Entity
@Table(name = "VACANCIES")
public class Vacancy implements Entity {

    @Id
    @GeneratedValue
    @Column(name = "VACANCY_ID")
    private Long id;

    @Column(name = "STATE")
    @Enumerated(value = EnumType.STRING)
    private VacancyState state;

    @Column(name = "HOT_FLAG")
    private boolean hot;

    @Column(name = "DESCRIPTION")
    private String requirements;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "VACANCIES_TAGS", joinColumns = {@JoinColumn(name = "VACANCY_ID")}, inverseJoinColumns = {@JoinColumn(name = "TAG_ID")})
    @JsonIgnore
    private Set<Tag> tags;

    @ManyToOne
    @JoinColumn(name = "PROJECT_ID")
    private Project project;

    @OneToMany(mappedBy = "vacancy", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Flow> flows = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "CREATOR_ID")
    private User creator;

//    private List<Comment> comments = new ArrayList<>();

    @Column(name = "CREATION_TIME")
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VacancyState getState() {
        return state;
    }

    public void setState(VacancyState state) {
        this.state = state;
    }

    public boolean isHot() {
        return hot;
    }

    public void setHot(boolean hot) {
        this.hot = hot;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<Flow> getFlows() {
        return flows;
    }

    public void setFlows(List<Flow> flows) {
        this.flows = flows;
    }

//    public List<Comment> getComments() {
//        return comments;
//    }
//
//    public void setComments(List<Comment> comments) {
//        this.comments = comments;
//    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(this.getClass().getSimpleName());
        builder.append(": {");
        builder.append("id: ");
        builder.append(id);
        builder.append(", state: ");
        builder.append(state);
        builder.append(", hot: ");
        builder.append(hot);
        builder.append(", requirements: ");
        builder.append(requirements);
        builder.append("}");
        return builder.toString();
    }
}
