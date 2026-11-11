package com.kandidato.model.dto;

import com.kandidato.constants.FieldType;
import com.kandidato.model.dto.tag.TaggableDTO;
import com.kandidato.persistence.entity.TimelineField;
import org.springframework.util.StringUtils;

import javax.validation.Valid;
import java.util.*;


public class CandidateDTO implements TaggableDTO {
    //hide id with IdentifierCodec
    private Long id;

    private Date createTime;

    @Valid
    private Set<TagDTO> tags;

    @Valid
    private Map<Date, TimelineRecordDTO> timelineRecords;

    @Valid
    private SummaryCardDTO summaryCard;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public Set<TagDTO> getTags() {
        return tags;
    }

    @Override
    public void setTags(Set<TagDTO> tags) {
        this.tags = tags;
    }

    public Map<Date, TimelineRecordDTO> getTimelineRecords() {
        return timelineRecords;
    }

    public void setTimelineRecords(Map<Date, TimelineRecordDTO> timelineRecords) {
        this.timelineRecords = timelineRecords;
    }

    public SummaryCardDTO getSummaryCard() {
        return summaryCard;
    }

    public void setSummaryCard(SummaryCardDTO summaryCard) {
        this.summaryCard = summaryCard;
    }

    public static SummaryCardDTO buildSummaryCard(List<TimelineField> fields) {
        Collections.sort(fields, new Comparator<TimelineField>() {
            @Override
            public int compare(TimelineField o1, TimelineField o2) {

                if (!o1.getType().equals(o2.getType())) {
                    return o1.getType().compareTo(o2.getType());
                }
                if (!o1.getCreateTime().equals(o2.getCreateTime())) {
                    return o2.getCreateTime().compareTo(o1.getCreateTime());
                }
                return o2.getParentUuid().compareTo(o1.getParentUuid());
            }
        });
        SummaryCardDTO summaryCard = new SummaryCardDTO();
        Map<UUID, TimelineFieldDTO> predefinedFields = new LinkedHashMap<>();
        Map<UUID, TimelineFieldDTO> primeFields = new LinkedHashMap<>();
        Map<UUID, TimelineFieldDTO> customFields = new LinkedHashMap<>();


        for (TimelineField timelineField : fields) {
            int priorityGroup = timelineField.getType().getPriorityGroup();
            int priority = timelineField.getType().getPriority();
            //TODO hack for name field - find better approach
            if (priority == 1 && priorityGroup == 1) {
                if (summaryCard.getName() == null) {
                    summaryCard.setName(TimelineFieldDTO.toDto(timelineField));
                }
                continue;
            }
            Map<UUID, TimelineFieldDTO> requiredGroup = null;
            switch (priorityGroup) {
                case 1:
                    requiredGroup = predefinedFields;
                    break;
                case 2:
                    requiredGroup = primeFields;
                    break;
                case 3:
                    requiredGroup = customFields;
                    break;
                default:
                    requiredGroup = customFields;
                    break;
            }
            TimelineFieldDTO currentValue = requiredGroup.get(timelineField.getParentUuid());
            if (currentValue == null) {
                requiredGroup.put(timelineField.getParentUuid(), TimelineFieldDTO.toDto(timelineField));
            }
        }
        if (summaryCard.getName() == null && !fields.isEmpty()) {
            //set first non=empty field as name
            summaryCard.setName(TimelineFieldDTO.toDto(fields.get(0)));
        }
        summaryCard.getPredefinedFields().addAll(predefinedFields.values());
        summaryCard.getPrimeFields().addAll(primeFields.values());
        summaryCard.getCustomFields().addAll(customFields.values());

        return summaryCard;
    }



    public static Map<Date, TimelineRecordDTO> buildTimeline(List<TimelineField> fields) {
        Collections.sort(fields);
        Map<Date, TimelineRecordDTO> timeline = new LinkedHashMap<>();
        for (TimelineField field : fields) {
            TimelineFieldDTO fieldDTO = TimelineFieldDTO.toDto(field);
            Date date = field.getCreateTime();
            TimelineRecordDTO recordDTO = timeline.get(date);
            if (recordDTO == null) {
                recordDTO = new TimelineRecordDTO(date);
                timeline.put(date, recordDTO);
            }
            recordDTO.getFields().add(fieldDTO);
        }
        return timeline;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CandidateDTO that = (CandidateDTO) o;

        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (tags != null ? !tags.equals(that.tags) : that.tags != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = createTime != null ? createTime.hashCode() : 0;
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(this.getClass().getSimpleName());
        builder.append(": {");
        builder.append("id: ");
        builder.append(id);
        builder.append(", createTime: ");
        builder.append(createTime);
        builder.append("}");
        return builder.toString();
    }
}
