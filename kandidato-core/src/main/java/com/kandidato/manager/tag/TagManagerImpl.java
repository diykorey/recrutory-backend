package com.kandidato.manager.tag;

import com.kandidato.persistence.repository.tag.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by diykorey on 04.04.2015.
 */
@Component
public class TagManagerImpl implements  TagManager {
    @Autowired
    private TagRepository tagRepository;
    @Override
    public void remove(long id) {
        tagRepository.delete(id);
    }
}
