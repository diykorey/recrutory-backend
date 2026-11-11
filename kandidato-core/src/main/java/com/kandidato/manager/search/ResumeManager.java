package com.kandidato.manager.search;

import com.kandidato.model.dto.BaseCandidateDTO;
import com.kandidato.model.dto.BaseUserDTO;
import com.kandidato.model.dto.ResumeDTO;
import com.kandidato.model.dto.UserDTO;
import com.kandidato.security.model.CurrentProfile;

public interface ResumeManager {
    BaseCandidateDTO createResume(CurrentProfile currentUser, ResumeDTO resume);
    BaseCandidateDTO createResume(CurrentProfile currentUser, long candidateId, ResumeDTO resume);
}
