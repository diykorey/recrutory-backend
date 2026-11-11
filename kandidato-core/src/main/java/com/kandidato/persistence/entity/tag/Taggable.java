package com.kandidato.persistence.entity.tag;

import java.util.Set;

/**
 * Created by andriy on 23.04.15.
 */
public interface Taggable {

    Set<Tag> getTags();

    void setTags(Set<Tag> tags);

}
