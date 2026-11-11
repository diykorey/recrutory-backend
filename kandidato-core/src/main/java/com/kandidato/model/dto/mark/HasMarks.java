package com.kandidato.model.dto.mark;

import java.util.Set;

/**
 * Created by andriy on 1/9/15.
 */
public interface HasMarks {

    Set<Mark> getMarks();

    void setMarks(Set<Mark> marks);

}
