package com.kandidato.model.dto;

import com.kandidato.constants.PredefinedTimelineFieldType;
import com.kandidato.persistence.entity.TimelineField;

import javax.validation.Valid;
import java.util.*;

public class SummaryCardDTO implements  Dto{
    private TimelineFieldDTO name;

    @Valid
    private List<TimelineFieldDTO> predefinedFields = new ArrayList<>();
    @Valid
    private List<TimelineFieldDTO> primeFields = new ArrayList<>();
    @Valid
    private List<TimelineFieldDTO> customFields = new ArrayList<>();
    public List<TimelineFieldDTO> getPredefinedFields() {
        return predefinedFields;
    }

    public void setPredefinedFields(List<TimelineFieldDTO> predefinedFields) {
        this.predefinedFields = predefinedFields;
    }

    public List<TimelineFieldDTO> getPrimeFields() {
        return primeFields;
    }

    public void setPrimeFields(List<TimelineFieldDTO> primeFields) {
        this.primeFields = primeFields;
    }

    public List<TimelineFieldDTO> getCustomFields() {
        return customFields;
    }

    public void setCustomFields(List<TimelineFieldDTO> customFields) {
        this.customFields = customFields;
    }

    public TimelineFieldDTO getName() {
        return name;
    }

    public void setName(TimelineFieldDTO name) {
        this.name = name;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SummaryCardDTO{");
        sb.append("name='").append(name).append('\'');
        sb.append(", predefinedFields=").append(predefinedFields);
        sb.append(", primeFields=").append(primeFields);
        sb.append(", customFields=").append(customFields);
        sb.append('}');
        return sb.toString();
    }
}
