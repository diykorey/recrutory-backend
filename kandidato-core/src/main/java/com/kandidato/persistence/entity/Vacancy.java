package com.kandidato.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kandidato.constants.SearchIndexState;
import com.kandidato.constants.VacancyState;
import com.kandidato.persistence.entity.comment.CommentableEntity;
import com.kandidato.persistence.entity.comment.VacancyComment;
import com.kandidato.persistence.entity.tag.Tag;
import com.kandidato.persistence.entity.tag.Taggable;

import javax.persistence.*;
import java.util.*;

@javax.persistence.Entity
@Table(name = "VACANCIES")
public class Vacancy implements CommentableEntity<VacancyComment>, CreatorAware, Taggable {

    @Id
    @GeneratedValue
    @Column(name = "VACANCY_ID")
    private Long id;

    @Column(name = "NUMBER")
    private String number;

    @Column(name = "NAME")
    private String name;

    @Column(name = "STATE")
    @Enumerated(value = EnumType.STRING)
    private VacancyState state;

    @Column(name = "SEARCH_INDEX_STATE")
    @Enumerated(EnumType.STRING)
    private SearchIndexState searchState = SearchIndexState.NEW;

    @Column(name = "HOT_FLAG")
    private boolean hot;

    @Column(name = "DESCRIPTION")
    private String requirements;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "VACANCIES_TAGS", joinColumns = {@JoinColumn(name = "VACANCY_ID")}, inverseJoinColumns = {@JoinColumn(name = "TAG_ID")})
    private Set<Tag> tags = new HashSet<>();

    @OneToMany(mappedBy = "vacancy", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Flow> flows = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User creator;

    @OneToMany(mappedBy = "entity", cascade = CascadeType.ALL, targetEntity = VacancyComment.class)
    @JsonIgnore
    private List<VacancyComment> comments = new ArrayList<>();

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

    public SearchIndexState getSearchState() {
        return searchState;
    }

    public void setSearchState(SearchIndexState searchState) {
        this.searchState = searchState;
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

    @Override
    public Set<Tag> getTags() {
        return tags;
    }

    @Override
    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public List<Flow> getFlows() {
        return flows;
    }

    public void setFlows(List<Flow> flows) {
        this.flows = flows;
    }

    @Override
    public List<VacancyComment> getComments() {
        return comments;
    }

    @Override
    public void setComments(List<VacancyComment> comments) {
        this.comments = comments;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(this.getClass().getSimpleName());
        builder.append(": {");
        builder.append("id: ");
        builder.append(id);
        builder.append("number: ");
        builder.append(number);
        builder.append("name: ");
        builder.append(name);
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
