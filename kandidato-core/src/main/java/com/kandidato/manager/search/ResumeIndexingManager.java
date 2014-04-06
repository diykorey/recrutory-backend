package com.kandidato.manager.search;


import com.kandidato.persistence.entity.Resume;
import com.kandidato.persistence.repository.resume.ResumeRepository;
import com.kandidato.persistence.repository.search.ResumeSearchRepository;
import com.kandidato.persistence.repository.search.TikaTransformer;
import com.kandidato.persistence.repository.search.elastic.ResumeDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ResumeIndexingManager {

    @Autowired
    private ResumeSearchRepository repository;

    @Autowired
    private ResumeRepository resumeRepository;

    @Autowired
    private TikaTransformer transformer;


    public void indexResumes(){
//        Collection<Resume> resumes = resumeRepository.query(new FindNotIndexed());
//
//        for(Resume resume : resumes){
//            addToIndex(resume);
//        }
    }

    private void addToIndex(Resume resume) {
        String text = transformer.textFrom(resume.getData());
        ResumeDocument doc = new ResumeDocument(0, 0, text);
        repository.index(doc);
    }
}
