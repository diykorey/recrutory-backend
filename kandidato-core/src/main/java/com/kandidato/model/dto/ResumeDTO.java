package com.kandidato.model.dto;


import com.kandidato.constants.SearchIndexState;
import com.kandidato.model.dto.input.CandidateIn;
import com.kandidato.persistence.entity.comment.ResumeComment;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResumeDTO implements Dto {

    private Long id;

    @NotNull
    private SearchIndexState state = SearchIndexState.NEW;

    @NotNull
    private BaseCandidateDTO candidate;

    private byte[] data;

    private String contentType;

    private String originalFileName;

    private List<ResumeComment> comments = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public List<ResumeComment> getComments() {
        return comments;
    }

    public void setComments(List<ResumeComment> comments) {
        this.comments = comments;
    }

    public SearchIndexState getState() {
        return state;
    }

    public void setState(SearchIndexState state) {
        this.state = state;
    }

    public BaseCandidateDTO getCandidate() {
        return candidate;
    }

    public void setCandidate(BaseCandidateDTO candidate) {
        this.candidate = candidate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResumeDTO)) return false;

        ResumeDTO resume = (ResumeDTO) o;

        if (!Arrays.equals(data, resume.data)) return false;
        if (state != resume.state) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = state.hashCode();
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }

    @Override
    public String toString() {
        return "ResumeDTO{" +
                "id=" + id +
                ", state=" + state +
                ", candidate=" + candidate +
                '}';
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }
}
