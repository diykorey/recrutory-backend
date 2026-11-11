package com.kandidato.model.dto;


import com.kandidato.persistence.entity.TimelineField;
import org.springframework.util.StringUtils;

import javax.validation.Valid;

/**
 * Created by diykorey on 11.02.2015.
 */
public class TimelineFieldDTO implements Dto {
    private Long id;

    @Valid
    private TimelineFieldTypeDTO type;

    private String fieldName;

    private String fieldValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public static TimelineFieldDTO toDto(TimelineField timelineField) {
        TimelineFieldDTO dto = new TimelineFieldDTO();
        dto.setId(timelineField.getId());
        dto.setType(TimelineFieldTypeDTO.toDto(timelineField.getType()));
        dto.setFieldName(getFieldName(timelineField));
        dto.setFieldValue(timelineField.getFieldValue());
        return dto;
    }

    private static String getFieldName(TimelineField timelineField) {
        if(StringUtils.hasText(timelineField.getName()))
            return timelineField.getName();

        return timelineField.getType().getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TimelineFieldDTO)) return false;

        TimelineFieldDTO fieldDTO = (TimelineFieldDTO) o;

        if (id != null ? !id.equals(fieldDTO.id) : fieldDTO.id != null) return false;
        if (!type.equals(fieldDTO.type)) return false;
        if (fieldName != null ? !fieldName.equals(fieldDTO.fieldName) : fieldDTO.fieldName != null) return false;
        return fieldValue.equals(fieldDTO.fieldValue);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + type.hashCode();
        result = 31 * result + (fieldName != null ? fieldName.hashCode() : 0);
        result = 31 * result + fieldValue.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TimelineFieldDTO{");
        sb.append("id=").append(id);
        sb.append(", type=").append(type);
        sb.append(", fieldName='").append(fieldName).append('\'');
        sb.append(", fieldValue='").append(fieldValue).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
