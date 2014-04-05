package com.kandidato.repository.resume;

import com.kandidato.entity.Resume;
import com.kandidato.repository.base.AbstractRepository;
import com.kandidato.repository.query.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;

@Repository
public class ResumeRepositoryImp extends AbstractRepository<Resume, Query> implements ResumeRepository{

    @Autowired
    protected ResumeRepositoryImp(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Collection<Resume> query(Query query) {
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
