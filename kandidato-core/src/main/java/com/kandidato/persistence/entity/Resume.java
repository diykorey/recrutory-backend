package com.kandidato.persistence.entity;


import com.kandidato.constants.ResumeState;

import javax.persistence.*;

@javax.persistence.Entity
@Table(name = "RESUME")
public class Resume implements Entity{

    @Id
    @Column(name = "RESUME_ID")
    @GeneratedValue
    private Long id;

    @Column(name = "STATE")
    @Enumerated(EnumType.STRING)
    private ResumeState state;

    @ManyToOne
    @JoinColumn(name = "RESUME_OWNER_ID")
    private Person resumeOwner;

    @Column(name = "DATA")
    @Lob
    private byte[] data;

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

    public ResumeState getState() {
        return state;
    }

    public void setState(ResumeState state) {
        this.state = state;
    }

    public Person getResumeOwner() {
        return resumeOwner;
    }

    public void setResumeOwner(Person resumeOwner) {
        this.resumeOwner = resumeOwner;
    }
}
