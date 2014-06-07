package com.kandidato.persistence.entity;

public class TimelineRecord implements Entity {

    private Long id;

    private Candidate candidate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }


}
