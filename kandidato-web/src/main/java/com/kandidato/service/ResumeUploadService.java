package com.kandidato.service;

import com.kandidato.manager.search.ResumeManager;
import com.kandidato.model.dto.*;
import com.kandidato.model.dto.input.CandidateIn;
import com.kandidato.security.auth.InjectUser;
import com.kandidato.security.model.CurrentProfile;
import com.kandidato.util.parser.GuessingNameParser;
import com.kandidato.util.parser.NameParser;
import com.wordnik.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

@Controller
@RequestMapping("/resume")
@Api(value = "resume", description = "ResumeUploadService Operations with resume", produces = "application/json")
public class ResumeUploadService extends  HttpAwareService {

    private static final Logger log = LoggerFactory.getLogger(ResumeUploadService.class);
    private static final String UPLOAD_FOLDER = "UPLOADED";

    @Autowired
    private ResumeManager resumeManager;

    @PostConstruct
    public void init() throws IOException {
        Path path = Paths.get(UPLOAD_FOLDER);
        if (!Files.exists(path)) {
            log.debug("Create folder for uploaded files");
            Files.createDirectory(path);
            return;
        }

        log.debug("Deleting recursivly: {}", path);
        //browsing the file directory and delete recursively using java nio
        Files.walkFileTree(path, new FileVisitor<Path>() {

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc)
                    throws IOException {
                // TODO Auto-generated method stub

                log.debug("deleting directory: {}", dir);
                Files.delete(dir);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult preVisitDirectory(Path dir,
                                                     BasicFileAttributes attrs) throws IOException {
                // TODO Auto-generated method stub
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file,
                                             BasicFileAttributes attrs) throws IOException {
                // TODO Auto-generated method stub
                log.debug("deleting file: {}", file);
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc)
                    throws IOException {
                // TODO Auto-generated method stub
                System.out.println(exc.toString());
                return FileVisitResult.CONTINUE;
            }
        });
    }

    /*@RequestMapping(value = "/uploadResumes", method = RequestMethod.GET)
    @ResponseBody
    public Map list() {
        log.debug("uploadGet called");
        Map<String, Object> files = new HashMap<>();
        files.put("files", new ArrayList<>());
        log.debug("Returning: {}", files);
        return files;
    }*/

    @RequestMapping(value = "/upload/list", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Map uploadList(@InjectUser CurrentProfile user, MultipartHttpServletRequest request) {
        log.debug("uploadPost called");
        Iterator<String> itr = request.getFileNames();
        MultipartFile mpf;
        List<Object> list = new LinkedList<>();

        while (itr.hasNext()) {
            mpf = request.getFile(itr.next());
            log.debug("Uploading {}", mpf.getOriginalFilename());

            try {
                ResumeDTO resume = new ResumeDTO();
                //resume.setCandidate(candidateFromFileName(mpf.getOriginalFilename()));
                resume.setData(mpf.getBytes());
                resume.setContentType(mpf.getContentType());
                resume.setOriginalFileName(mpf.getOriginalFilename());
                resumeManager.createResume(user, resume);
            } catch (IOException e) {
                log.error("Could not upload file {}", mpf.getOriginalFilename(), e);
                log.debug("Stacktrace: {}", e);
            }

        }

        Map<String, Object> files = new HashMap<>();
        files.put("files", list);
        return files;
    }

    @RequestMapping(value = "/upload/{candidateId}", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public BaseCandidateDTO upload(@InjectUser CurrentProfile user, @PathVariable long candidateId, MultipartHttpServletRequest request) {
        log.debug("uploadPost called");
        Iterator<String> itr = request.getFileNames();
        MultipartFile mpf;
        List<Object> list = new LinkedList<>();

        if (itr.hasNext()) {
            mpf = request.getFile(itr.next());
            log.debug("Uploading {}", mpf.getOriginalFilename());

            try {
                ResumeDTO resume = new ResumeDTO();
                //resume.setCandidate(candidateFromFileName(mpf.getOriginalFilename()));
                resume.setData(mpf.getBytes());
                resume.setContentType(mpf.getContentType());
                resume.setOriginalFileName(mpf.getOriginalFilename());
                return resumeManager.createResume(user, candidateId, resume);
            } catch (IOException e) {
                log.error("Could not upload file {}", mpf.getOriginalFilename(), e);
                log.debug("Stacktrace: {}", e);
            }

        }

        return null;
    }
    @RequestMapping(value = "/upload/one", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public BaseCandidateDTO uploadOne(@InjectUser CurrentProfile user, MultipartHttpServletRequest request) {
        log.debug("uploadPost called");
        Iterator<String> itr = request.getFileNames();
        MultipartFile mpf;
        List<Object> list = new LinkedList<>();

        if (itr.hasNext()) {
            mpf = request.getFile(itr.next());
            log.debug("Uploading {}", mpf.getOriginalFilename());

            try {
                ResumeDTO resume = new ResumeDTO();
                //resume.setCandidate(candidateFromFileName(mpf.getOriginalFilename()));
                resume.setData(mpf.getBytes());
                resume.setContentType(mpf.getContentType());
                resume.setOriginalFileName(mpf.getOriginalFilename());
                return resumeManager.createResume(user, resume);
            } catch (IOException e) {
                log.error("Could not upload file {}", mpf.getOriginalFilename(), e);
                log.debug("Stacktrace: {}", e);
            }

        }

        return null;
    }

    //TODO move it on manager
    private CandidateIn candidateFromFileName(String fileName) {
        CandidateIn candidate = new CandidateIn();
        NameParser.PersonName name = new GuessingNameParser(fileName).parseName();
        //TODO add name surname as timeline fields!!!
        /*TimelineRecordDTO record = new TimelineRecordDTO();
        List<TimelineFieldDTO> fields = new ArrayList<>();
        TimelineFieldDTO field = new TimelineFieldDTO();
        TimelineFieldTypeDTO fieldType = new TimelineFieldTypeDTO();
        fieldType.setName();
        fieldType.setPrime(true);
        field.setType(fieldType);
        field.setFieldValue(name.getFirstName() + " " + name.getLastName());
        fields.add(field);
        record.setFields(fields);
        List<TimelineRecordDTO> timelineRecords = new ArrayList<>(1);
        candidate.setTimelineFields(timelineRecords);*/
       /* candidate.setFirstName(name.getFirstName());
        candidate.setLastName(name.getLastName());*/
        return candidate;
    }


}
