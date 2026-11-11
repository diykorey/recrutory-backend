package com.kandidato.model.dto.mark;

import com.kandidato.model.dto.Dto;

/**
 * Interface, used to mark existing entities with additional info.
 * <p/>
 * For example, a Vacancy may have 3 matching candidates, thus, there will be 3 marks, put on the vacancy.
 *
 * Created by andriy on 1/9/15.
 */
public interface Mark extends Dto {

    /**
     * Returns the id of the object, to which the mark is attached.
     *
     * @return
     */
    Long getMarkedObjectId();

}
