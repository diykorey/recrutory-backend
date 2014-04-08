package com.kandidato.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FlowDto implements Dto {

    private Long id;

    private PersonDto person;

    private VacancyDto vacancy;

    private List<FlowActionDto> actions = new ArrayList<>();
    private Date createTime;

    //TODO Do we need active flag here? As for me it overlaps with the FlowState of the last action in the current flow.
    private boolean active = true;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PersonDto getPerson() {
        return person;
    }

    public void setPerson(PersonDto person) {
        this.person = person;
    }

    public VacancyDto getVacancy() {
        return vacancy;
    }

    public void setVacancy(VacancyDto vacancy) {
        this.vacancy = vacancy;
    }

    public List<FlowActionDto> getActions() {
        return actions;
    }

    public void setActions(List<FlowActionDto> actions) {
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
