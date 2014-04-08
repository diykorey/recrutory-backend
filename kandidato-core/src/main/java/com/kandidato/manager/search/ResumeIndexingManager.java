package com.kandidato.manager.search;


import com.kandidato.persistence.entity.Resume;
import com.kandidato.persistence.search.ResumeSearchRepository;
import com.kandidato.persistence.search.elastic.ResumeDocument;
import com.kandidato.util.TikaTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.repository.support.ElasticsearchRepositoryFactory;
import org.springframework.stereotype.Component;

@Component
public class ResumeIndexingManager {

    @Autowired
    private ElasticsearchRepositoryFactory elasticSearchRepositoryFactory;
//
//    @Autowired
//    private ResumeRepository resumeRepository;

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
        ResumeDocument doc = new ResumeDocument(0l, 0, 0, text);

        ResumeSearchRepository resumeSearchRepository = //
                elasticSearchRepositoryFactory.getRepository(ResumeSearchRepository.class);

        resumeSearchRepository.index(doc);
    }
}
