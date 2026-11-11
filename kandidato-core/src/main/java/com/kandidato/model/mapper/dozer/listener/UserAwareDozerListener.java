package com.kandidato.model.mapper.dozer.listener;

import com.kandidato.persistence.entity.CreatorAware;
import com.kandidato.persistence.entity.User;
import org.dozer.DozerEventListener;
import org.dozer.event.DozerEvent;
import org.springframework.stereotype.Component;

/**
 * Created by andriy on 1/13/15.
 */
@Component
public class UserAwareDozerListener implements DozerEventListener {

    public UserAwareDozerListener() {
    }

    @Override
    public void mappingStarted(DozerEvent event) {

    }

    @Override
    public void preWritingDestinationValue(DozerEvent event) {

    }

    @Override
    public void postWritingDestinationValue(DozerEvent event) {

    }

    @Override
    public void mappingFinished(DozerEvent event) {
        if (event.getDestinationObject() instanceof CreatorAware) {
            addUserDataToEntity((CreatorAware) event.getDestinationObject());
        }
    }

    private void addUserDataToEntity(CreatorAware destinationObject) {
        User user = new User();
        user.setId(1L);
        destinationObject.setCreator(user);
    }
}
