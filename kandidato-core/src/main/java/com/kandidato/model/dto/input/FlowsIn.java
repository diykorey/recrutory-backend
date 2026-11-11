package com.kandidato.model.dto.input;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class FlowsIn {

    @NotNull
    @Size(min = 1)
    private List<FlowIn> flows;

    public List<FlowIn> getFlows() {
        return flows;
    }

    public void setFlows(List<FlowIn> flows) {
        this.flows = flows;
    }
}
