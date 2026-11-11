package com.kandidato.model.dto.input;

import com.kandidato.model.dto.TimelineFieldTypeDTO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class FieldIn {

    @Valid
    private TimelineFieldTypeDTO type;

    private String fieldName;

    private String fieldValue;

    public TimelineFieldTypeDTO getType() {
        return type;
    }

    public void setType(TimelineFieldTypeDTO type) {
        this.type = type;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FieldIn{");
        sb.append("type=").append(type);
        sb.append(", fieldName='").append(fieldName).append('\'');
        sb.append(", fieldValue='").append(fieldValue).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
