package com.kandidato.model.dto;

import com.kandidato.model.dto.tag.TaggableDTO;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Set;


public class BaseCandidateDTO implements TaggableDTO {

    private Long id;

    private Date createTime;

    @Valid
    private Set<TagDTO> tags;

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

    public SummaryCardDTO getSummaryCard() {
        return summaryCard;
    }

    public void setSummaryCard(SummaryCardDTO summaryCard) {
        this.summaryCard = summaryCard;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BaseCandidateDTO{");
        sb.append("id=").append(id);
        sb.append(", createTime=").append(createTime);
        sb.append(", tags=").append(tags);
        sb.append(", summaryCard=").append(summaryCard);
        sb.append('}');
        return sb.toString();
    }
}
