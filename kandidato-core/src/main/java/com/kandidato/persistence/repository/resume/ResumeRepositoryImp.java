package com.kandidato.persistence.repository.resume;

import com.kandidato.persistence.entity.Resume;
import com.kandidato.persistence.repository.base.HibernateRepository;
import com.kandidato.persistence.repository.query.HibernateQuery;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;

public class ResumeRepositoryImp extends HibernateRepository<Resume, HibernateQuery> implements ResumeRepositoryCustom {

    @Autowired
    public ResumeRepositoryImp(EntityManager entityManager) {
        super(entityManager, Resume.class);
    }

    @Override
    public Collection<Resume> query(HibernateQuery query) {
        Path file = Paths.get("E:\\Mykola_Kavf_CV.pdf");
        try {
            byte[] data = Files.readAllBytes(file);
            Resume resume = new Resume();
            resume.setData(data);

            return Arrays.asList(resume);
        } catch (IOException e) {
            return null;
        }
    }
}
