package com.kandidato.security.util;

import com.kandidato.security.model.KandidatoUser;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by andriy on 7/26/14.
 */
public class SessionUtil {

    public static final long getCurrentUserId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof KandidatoUser) {
            return ((KandidatoUser) principal).getId();
        }
        throw new IllegalStateException("Principal instance is of unknown type");
    }
}
