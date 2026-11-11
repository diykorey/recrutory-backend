package com.kandidato.manager.search;

import com.kandidato.persistence.entity.Candidate;
import com.kandidato.persistence.entity.Resume;
import com.kandidato.persistence.repository.resume.ResumeRepository;
import com.kandidato.persistence.search.repository.ResumeSearchRepository;
import com.kandidato.persistence.search.model.ResumeDocument;
import com.kandidato.util.TikaTransformer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.LinkedList;
import java.util.List;

import static com.kandidato.constants.SearchIndexState.*;
import static org.easymock.EasyMock.*;

/**
 * Created by andriy on 11/22/14.
 */
@RunWith(PowerMockRunner.class)
public class ResumeIndexingManagerTest {

    public static final String TEST_RESUME_CONTENT = "Test resume content";
    private ResumeIndexingManagerImpl resumeIndexManager;
    private ResumeSearchRepository resumeSearchRepository = PowerMock.createMock(ResumeSearchRepository.class);
    private ResumeRepository resumeRepository = PowerMock.createMock(ResumeRepository.class);
    private TikaTransformer tikaTransformer = PowerMock.createMock(TikaTransformer.class);
    private BlobManager blobManager = PowerMock.createMock(BlobManager.class);

    @Before
    public void setup() {
        resumeIndexManager = new ResumeIndexingManagerImpl();

        ReflectionTestUtils.setField(resumeIndexManager, "resumeSearchRepository", resumeSearchRepository);
        ReflectionTestUtils.setField(resumeIndexManager, "resumeRepository", resumeRepository);
        ReflectionTestUtils.setField(resumeIndexManager, "tikaTransformer", tikaTransformer);
        ReflectionTestUtils.setField(resumeIndexManager, "blobManager", blobManager);


    }


    /**
     * The following test creates a set of resumes to index and emulates different indexeing failures on some of the
     * resumes, it then checks if the indexing manager has set the state of the resumes to
     * {@link com.kandidato.constants.SearchIndexState.ERROR} or {@link com.kandidato.constants.SearchIndexState.INDEXED}.
     */
    @Test
    @Ignore
    public void shouldMarkResumesWithErrors() throws Exception {

        List<Resume> resumes = new LinkedList<>();

        Candidate owner = new Candidate();
        owner.setId(1L);

        Resume validResume = createResume(owner, 1L);
        Resume invalidResume = createResume(owner, 2L);
        Resume invalidResume1 = createResume(owner, 3L);

        resumes.add(validResume);
        resumes.add(invalidResume);
        resumes.add(invalidResume1);

        expect(resumeRepository.findByStateIs(NEW, new PageRequest(0, 5))).andReturn(resumes);
        expect(blobManager.download("")).andReturn(new byte[1]).anyTimes();

        expect(tikaTransformer.textFrom(isA(byte[].class))).andReturn(TEST_RESUME_CONTENT).times(2);
        //returning an empty string, since this is what tika library does, when parsing error occurs
        expect(tikaTransformer.textFrom(isA(byte[].class))).andReturn("").once();

        expect(resumeSearchRepository.index(eq(new ResumeDocument(validResume, owner, TEST_RESUME_CONTENT))))
                .andReturn(null);
        expect(resumeSearchRepository.index(eq(new ResumeDocument(invalidResume, owner, TEST_RESUME_CONTENT))))
                .andThrow(new RuntimeException());
        expect(resumeSearchRepository.index(eq(new ResumeDocument(invalidResume1, owner, TEST_RESUME_CONTENT))))
                .andThrow(new RuntimeException());

        expect(resumeRepository.saveAndFlush(validResume)).andReturn(null);
        expect(resumeRepository.saveAndFlush(invalidResume)).andReturn(null);
        expect(resumeRepository.saveAndFlush(invalidResume1)).andReturn(null);

        PowerMock.replayAll();

        resumeIndexManager.indexResumes();

        Assert.assertTrue(validResume.getState() == INDEXED);
        Assert.assertTrue(invalidResume.getState() == ERROR);
        Assert.assertTrue(invalidResume1.getState() == ERROR);

    }

    private Resume createResume(Candidate owner, Long id) {
        Resume invalidResume1 = new Resume();
        invalidResume1.setId(id);
        invalidResume1.setCandidate(owner);
        invalidResume1.setDataUrl("");
        return invalidResume1;
    }

}
