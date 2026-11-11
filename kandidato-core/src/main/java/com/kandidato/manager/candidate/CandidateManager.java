package com.kandidato.manager.candidate;

import com.kandidato.model.dto.*;
import com.kandidato.model.dto.input.CandidateIn;
import com.kandidato.model.dto.input.TagsIn;
import com.kandidato.persistence.entity.User;
import com.kandidato.security.model.CurrentProfile;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface CandidateManager {

    BaseCandidateDTO create(CurrentProfile currentUser, CandidateIn candidateIn);

    BaseCandidateDTO getCandidate(long id);

    void remove(long id);

    void update(CandidateDTO candidate);

    List<BaseCandidateDTO> findByOwner(long ownerId);

    List<BaseCandidateDTO> findAvailable(long vacancyId, long creatorId, int page, int size);

    BaseCandidateDTO addField(long userId, long candidateId, TimelineFieldDTO field);

    BaseCandidateDTO updateField(long fieldId, TimelineFieldDTO field);

    void removeField(long fieldId);

    List<TimelineFieldTypeDTO> findFieldTypes(long userId);

    List<BaseCandidateDTO> find(Long id, String sortBy, String direction, int page, int size);

    //TODO this duplicates tags API in VacancyManager
    BaseCandidateDTO addTags(long candidateId, TagsIn tagsIn);

    void removeTag(long candidateId, long tagId);

    CandidateDTO getCandidateProfile(long id);

    List<BaseCandidateDTO> filterByNameAndEmail(String name, String email, User user, String sortBy, Sort.Direction direction, int page, int size);
}
