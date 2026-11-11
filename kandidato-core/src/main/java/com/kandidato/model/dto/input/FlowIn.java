package com.kandidato.model.dto.input;

import javax.validation.constraints.NotNull;

public class FlowIn {

    @NotNull
    private Long vacancyId;

    @NotNull
    private Long candidateId;

    public Long getVacancyId() {
        return vacancyId;
    }

    public void setVacancyId(Long vacancyId) {
        this.vacancyId = vacancyId;
    }

    public Long getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Long candidateId) {
        this.candidateId = candidateId;
    }
}
