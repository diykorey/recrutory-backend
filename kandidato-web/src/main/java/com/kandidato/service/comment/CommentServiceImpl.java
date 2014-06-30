package com.kandidato.service.comment;

import com.kandidato.constants.CommentType;
import com.kandidato.manager.comment.CommentManager;
import com.kandidato.persistence.entity.comment.EntityComment;
import com.kandidato.service.HttpAwareService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by andriy on 4/26/14.
 */
@Controller
@RequestMapping("/comment")
public class CommentServiceImpl extends HttpAwareService {

    private static final Logger log = LoggerFactory.getLogger(CommentServiceImpl.class);

    @Autowired
    private CommentManager manager;

    //    @Override
    @RequestMapping(value = "/{type}/{entityId}/{authorId}", method = RequestMethod.POST, consumes = "text/plain")
    @Transactional
    @ResponseStatus(value = HttpStatus.OK)
    public void addComment(@PathVariable long entityId, @PathVariable long authorId, @PathVariable CommentType type, @RequestBody String comment) {
        log.info("Adding comment");
        this.manager.addComment(entityId, authorId, type, comment);
    }

    @RequestMapping(value = "/{type}/{entityId}", method = RequestMethod.GET, produces = "application/json")
    @Transactional
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public List<EntityComment<?>> getComments(@PathVariable CommentType type, @PathVariable long entityId) {
        return this.manager.getEntityComments(entityId, type);
    }
}
