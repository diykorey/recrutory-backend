package com.kandidato.entity;


import com.kandidato.constants.ResumeState;

import java.util.Date;

public class Resume implements Entity{
    private long id;
    private Person owner;
    private Date createTime;
    private Date indexingTime;
    private boolean latest;
    private ResumeState state;
    private byte[] data;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ResumeState getState() {
        return state;
    }

    public void setState(ResumeState state) {
        this.state = state;
    }

    public boolean isLatest() {
        return latest;
    }

    public void setLatest(boolean latest) {
        this.latest = latest;
    }

    public Date getIndexingTime() {
        return indexingTime;
    }

    public void setIndexingTime(Date indexingTime) {
        this.indexingTime = indexingTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
