package com.kandidato.model.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FlowDTO implements Dto {
    private Long id;

    @NotNull
    private BaseCandidateDTO candidate;

    @NotNull
    private BaseVacancyDTO vacancy;

    @Valid
    private List<FlowActionDTO> actions = new ArrayList<>();
    private Date createTime;
    private boolean active = true;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<FlowActionDTO> getActions() {
        return actions;
    }

    public void setActions(List<FlowActionDTO> actions) {
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

    public BaseVacancyDTO getVacancy() {
        return vacancy;
    }

    public void setVacancy(BaseVacancyDTO vacancy) {
        this.vacancy = vacancy;
    }

    public BaseCandidateDTO getCandidate() {
        return candidate;
    }

    public void setCandidate(BaseCandidateDTO candidate) {
        this.candidate = candidate;
    }
}
