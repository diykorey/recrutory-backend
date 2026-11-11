package com.kandidato.model.dto.mark;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by andriy on 1/11/15.
 */
public class MarkedIdentifier implements HasMarks {

    private Long markedId;

    private Set<Mark> marks = new HashSet<>();

    public MarkedIdentifier(Long markedId, Set<Mark> marks) {
        this.markedId = markedId;
        this.marks = marks;
    }

    public Long getMarkedId() {
        return markedId;
    }

    @Override
    public Set<Mark> getMarks() {
        return new HashSet<>(marks);
    }

    @Override
    public void setMarks(Set<Mark> marks) {
        this.marks.clear();
        this.marks.addAll(marks);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MarkedIdentifier)) return false;

        MarkedIdentifier markedIdentifier = (MarkedIdentifier) o;

        if (!this.markedId.equals(markedIdentifier.markedId)) return false;
        if (!marks.equals(markedIdentifier.marks)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = markedId.hashCode();
        result = 31 * result + marks.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "MarkedIdentifier{" +
                "markedId=" + markedId +
                ", marks=" + marks +
                '}';
    }
}
