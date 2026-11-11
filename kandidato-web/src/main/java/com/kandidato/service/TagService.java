package com.kandidato.service;

import com.kandidato.manager.tag.TagManager;
import com.kandidato.model.dto.TagDTO;
import com.wordnik.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/tag")
@Api(value = "tag", description = "TagService operations with tags", produces = "application/json")
public class TagService {

    private static final Logger log = LoggerFactory.getLogger(TagService.class);

    @Autowired
    private TagManager tagManager;

    @RequestMapping(value = "/find", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @Transactional
    public List<TagDTO> find(@RequestParam(value = "query", required = false) String query, @RequestParam(value = "limit", required = false) Integer limit, @RequestParam(value = "page", required = false) Integer page) {
        if (page > 1)
            return new ArrayList<>();
        List<TagDTO> tags = new ArrayList<>();
        for (long i = 0; i < 7; i++) {
            TagDTO tag = new TagDTO();
            tag.setId(i);
            tag.setKeyword("tag-" + i);
            tags.add(tag);
        }
        return tags;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    public void remove(@PathVariable("id") long id) {
        tagManager.remove(id);
    }


}
