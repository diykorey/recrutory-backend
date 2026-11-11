package com.kandidato.model.dto.input;

import com.kandidato.constants.FlowState;

import javax.validation.constraints.NotNull;

public class DescriptionIn {

    @NotNull
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
