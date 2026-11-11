package com.kandidato.manager.tag;

import com.kandidato.model.dto.VacancyDTO;
import com.kandidato.model.dto.input.TagsIn;
import com.kandidato.model.dto.tag.TaggableDTO;
import com.kandidato.persistence.entity.tag.Tag;
import com.kandidato.persistence.entity.tag.Taggable;
import com.kandidato.util.DozerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by andriy on 23.04.15.
 */
public class TaggableManager<T extends TaggableDTO> {
//
//    private static final Logger log = LoggerFactory.getLogger(TaggableManager.class);
//
//    @Autowired
//    private DozerUtil dozerUtil;
//
//    @Override
//    public T addTags(long entityId, TagsIn tagsIn) {
//        log.debug("add tags to entity id: {}", entityId);
//        Taggable vacancy = repository.findOne(entityId);
//
//        Set<Tag> tagList = dozerUtil.map(tagsIn, Set.class);
//        vacancy.getFields().addAll(tagList);
//
////        for (String tag : tagsIn.getFields()) {
////            Tag newTag = new Tag();
////            newTag.setKeyword(tag);
////
////            vacancy.getFields().add(newTag);
////        }
//
//        return dozerUtil.map(repository.save(vacancy),
//                TaggableDTO.class);
//    }
//
//    public void removeTag(long entityId, long tagId) {
//        log.debug("remove tag {} from entity id: {}", tagId, entityId);
//        Taggable vacancy = repository.findOne(entityId);
//        for (Iterator<Tag> iterator = vacancy.getFields().iterator(); iterator.hasNext(); ) {
//            Tag tag = iterator.next();
//            if (tag.getId() == tagId) {
//                iterator.remove();
//                break;
//            }
//        }
//        repository.save(vacancy);
//    }

}
