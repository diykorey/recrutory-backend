package com.kandidato.dto;

import com.kandidato.constants.VacancyState;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class VacancyDto implements com.kandidato.dto.Dto {

    private long id;

    private VacancyState state;

    private boolean hot;

    private String requirements;

    private Set<TagDto> tags;

    private ProjectDto project;

    private List<FlowDto> flows = new ArrayList<>();

    private UserDto creator;

//    private List<CommentDto> comments = new ArrayList<>();

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

    public Set<TagDto> getTags() {
        return tags;
    }

    public void setTags(Set<TagDto> tags) {
        this.tags = tags;
    }

    public ProjectDto getProject() {
        return project;
    }

    public void setProject(ProjectDto project) {
        this.project = project;
    }

    public List<FlowDto> getFlows() {
        return flows;
    }

    public void setFlows(List<FlowDto> flows) {
        this.flows = flows;
    }

//    public List<CommentDto> getComments() {
//        return comments;
//    }
//
//    public void setComments(List<CommentDto> comments) {
//        this.comments = comments;
//    }

    public UserDto getCreator() {
        return creator;
    }

    public void setCreator(UserDto creator) {
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
