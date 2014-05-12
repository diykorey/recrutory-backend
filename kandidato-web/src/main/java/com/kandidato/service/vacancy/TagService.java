package com.kandidato.service.vacancy;


import com.kandidato.persistence.entity.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * RESTful implementation of {@link com.kandidato.service.vacancy.VacancyService}.
 */
@Controller
@RequestMapping("/tag")
public class TagService {

    private static final Logger log = LoggerFactory.getLogger(TagService.class);


    @RequestMapping(value = "/find", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @Transactional
    public List<Tag> find(@RequestParam("query") String query, @RequestParam("limit") int limit, @RequestParam("page") int page) {
        if (page > 1)
            return new ArrayList<>();
        List<Tag> tags = new ArrayList<>();
        for (long i = 0; i < 7; i++) {
            Tag tag = new Tag();
            tag.setId(i);
            tag.setKeyword("keyword" + i);
            tags.add(tag);
        }
        return tags;
    }


}
