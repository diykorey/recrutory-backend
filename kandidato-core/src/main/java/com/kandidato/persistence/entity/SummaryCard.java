package com.kandidato.persistence.entity;

import com.kandidato.constants.PredefinedTimelineFieldType;

import java.io.Serializable;
import java.util.*;

public class SummaryCard  implements Serializable {
   private Map<PredefinedTimelineFieldType, TimelineField> predefinedFields = new TreeMap<>();

    private Map<String, TimelineField> customFields = new TreeMap<>();

    public Map<String, TimelineField> getCustomFields() {
        return customFields;
    }

    public void setCustomFields(Map<String, TimelineField> customFields) {
        this.customFields.clear();
        this.customFields.putAll(customFields);
    }

    public Map<PredefinedTimelineFieldType, TimelineField> getPredefinedFields() {
        return predefinedFields;
    }

    public void setPredefinedFields(Map<PredefinedTimelineFieldType, TimelineField> predefinedFields) {
        this.predefinedFields.clear();
        this.predefinedFields.putAll(predefinedFields);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(this.getClass().getSimpleName());
        builder.append(": {");
        builder.append("predefinedFields: ");
        builder.append(predefinedFields);
        builder.append("customFields: ");
        builder.append(customFields);
        builder.append("}");
        return builder.toString();
    }
}

class PrdefinedFiledsComparator implements Comparator<TimelineField> {

    @Override
    public int compare(TimelineField o1, TimelineField o2) {
        return 0;
    }
}
