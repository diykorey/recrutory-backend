package com.kandidato.model.dto;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TimelineRecordDTO {

    @Valid
    private List<TimelineFieldDTO> fields = new ArrayList<>();

    private Date createTime;



    public TimelineRecordDTO() {
    }
    public TimelineRecordDTO(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<TimelineFieldDTO> getFields() {
        return fields;
    }

    public void setFields(List<TimelineFieldDTO> fields) {
        this.fields = fields;
    }
}
