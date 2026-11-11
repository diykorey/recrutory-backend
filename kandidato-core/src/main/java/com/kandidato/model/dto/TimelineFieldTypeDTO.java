package com.kandidato.model.dto;

import com.kandidato.persistence.entity.TimelineFieldType;

/**
 * Created by diykorey on 11.02.2015.
 */
public class TimelineFieldTypeDTO implements Dto {
    private Long id;

    private String name;

    private int priorityGroup;

    private int priority;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPriorityGroup() {
        return priorityGroup;
    }

    public void setPriorityGroup(int priorityGroup) {
        this.priorityGroup = priorityGroup;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public static TimelineFieldTypeDTO toDto(TimelineFieldType fieldType) {
        TimelineFieldTypeDTO dto = new TimelineFieldTypeDTO();
        dto.setId(fieldType.getId());
        dto.setName(fieldType.getName());
        dto.setPriorityGroup(fieldType.getPriorityGroup());
        dto.setPriority(fieldType.getPriority());
        return dto;

    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TimelineFieldTypeDTO{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", priorityGroup=").append(priorityGroup);
        sb.append(", priority=").append(priority);
        sb.append('}');
        return sb.toString();
    }
}
