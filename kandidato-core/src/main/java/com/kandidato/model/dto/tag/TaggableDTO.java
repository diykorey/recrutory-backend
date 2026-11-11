package com.kandidato.model.dto.tag;

import com.kandidato.model.dto.Dto;
import com.kandidato.model.dto.TagDTO;

import java.util.Set;

/**
 * Created by andriy on 23.04.15.
 */
public interface TaggableDTO extends Dto {

    Set<TagDTO> getTags();

    void setTags(Set<TagDTO> tags);
}
