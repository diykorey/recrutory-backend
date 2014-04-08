package com.kandidato.persistence.entity;


import javax.persistence.*;

@javax.persistence.Entity
@Table(name = "RESUME")
public class Resume implements Entity{

    @Id
    @Column(name = "RESUME_ID")
    @GeneratedValue
    private Long id;

    @Column(name = "RESUME_DATA")
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
}
