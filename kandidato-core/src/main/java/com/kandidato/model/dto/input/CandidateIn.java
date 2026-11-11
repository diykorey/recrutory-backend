package com.kandidato.model.dto.input;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


public class CandidateIn  {

    @Valid
    private List<String> tags = new ArrayList<>();

    @Valid
    private List<FieldIn> fields = new ArrayList<>();

    public List<FieldIn> getFields() {
        return fields;
    }

    public void setFields(List<FieldIn> fields) {
        this.fields = fields;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CandidateIn{");
        sb.append(", tags=").append(tags);
        sb.append(", fields=").append(fields);
        sb.append('}');
        return sb.toString();
    }
}
