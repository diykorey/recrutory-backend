package com.kandidato.service.candidate;

import com.kandidato.config.MainConfig;
import com.kandidato.config.WebMvcConfig;
import com.kandidato.manager.candidate.CandidateManager;
import com.kandidato.manager.user.UserManager;
import com.kandidato.model.dto.BaseCandidateDTO;
import com.kandidato.model.dto.TimelineFieldTypeDTO;
import com.kandidato.model.dto.input.CandidateIn;
import com.kandidato.model.dto.input.FieldIn;
import com.kandidato.persistence.entity.User;
import com.kandidato.security.model.CurrentProfile;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by diykorey on 15-Oct-15.
 */

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {MainConfig.class, WebMvcConfig.class})
//@WebAppConfiguration
public class CandidateManagerTest {
    @Autowired
    CandidateManager candidateManager;

    //@Test

    public void test() {
        User user = new User(1L);
        CurrentProfile currentUser = new CurrentProfile(user, UserManager.GOOGLE, "test@test.com", "unit", "test");
        CandidateIn candidateIn = getCandidateIn();
        BaseCandidateDTO candidate = candidateManager.create(currentUser, candidateIn);

    }

    private CandidateIn getCandidateIn() {
        CandidateIn candidate = new CandidateIn();
        List<FieldIn> fields = new ArrayList<>();
        FieldIn field1 = createField("fieldName1", "fieldValue1", 1L);
        FieldIn field2 = createField("fieldName2", "fieldValue2", 2L);
        FieldIn field3 = createField("fieldName3", "fieldValue3");

        fields.add(field1);
        fields.add(field2);
        fields.add(field3);

        candidate.setFields(fields);
        List<String> tags = new ArrayList<>();
        tags.add("tag1");
        tags.add("tag2");
        candidate.setTags(tags);
        return candidate;
    }

    private FieldIn createField(String name, String value, long typeId) {
        FieldIn field = new FieldIn();
        field.setFieldName(name);
        field.setFieldValue(value);
        TimelineFieldTypeDTO type = new TimelineFieldTypeDTO();
        type.setId(typeId);
        field.setType(type);
        return field;
    }
    private FieldIn createField(String name, String value) {
        FieldIn field = new FieldIn();
        field.setFieldName(name);
        field.setFieldValue(value);
        TimelineFieldTypeDTO type = new TimelineFieldTypeDTO();
        type.setName("TEST_TYPE");
        field.setType(type);
        return field;
    }


}
