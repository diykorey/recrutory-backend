package com.kandidato.service;

import com.kandidato.constants.CommentType;
import com.kandidato.manager.comment.CommentManager;
import com.kandidato.model.dto.BaseUserDTO;
import com.kandidato.model.dto.input.CommentIn;
import com.kandidato.persistence.entity.comment.EntityComment;
import com.kandidato.security.auth.InjectUser;
import com.kandidato.security.model.CurrentProfile;
import com.wordnik.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@Controller
@RequestMapping("/comment")
@Api(value = "comment", description = "CommentServiceImpl Operations with comments", produces = "application/json")
public class CommentService extends HttpAwareService {

    private static final Logger log = LoggerFactory.getLogger(CommentService.class);

    @Autowired
    private CommentManager manager;

    @RequestMapping( method = RequestMethod.POST, consumes = "application/json")
    @Transactional
    @ResponseStatus(value = HttpStatus.OK)
    public void addComment(@InjectUser CurrentProfile user, @RequestBody CommentIn comment) {
        log.info("Adding comment");
        long authorId = user.getId();
        manager.addComment(authorId, comment.getEntityId(), comment.getType(), comment.getText());
    }

    @RequestMapping(value = "/{commentId}", method = RequestMethod.POST, consumes = "application/json")
    @Transactional
    @ResponseStatus(value = HttpStatus.OK)
    public void updateComment(@InjectUser CurrentProfile user, @PathVariable long commentId, @RequestBody CommentIn comment) {
        log.info("Updating comment");
        manager.updateComment(commentId, comment.getText());
    }

    @RequestMapping(value = "/{commentId}", method = RequestMethod.DELETE)
    @Transactional
    @ResponseStatus(value = HttpStatus.OK)
    public void removeComment(@InjectUser CurrentProfile user, @PathVariable long commentId) {
        log.info("Removing comment");
        manager.removeComment(commentId);
    }



    @RequestMapping(value = "/{type}/{entityId}", method = RequestMethod.GET, produces = "application/json")
    @Transactional
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public List<EntityComment<?>> getComments(@PathVariable CommentType type, @PathVariable long entityId) {
        return manager.getEntityComments(entityId, type);
    }
}
