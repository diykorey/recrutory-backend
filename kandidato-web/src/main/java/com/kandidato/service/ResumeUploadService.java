package com.kandidato.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.PostConstruct;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

/**
 * Created by diyko on 04.04.2014.
 */
@Controller
public class ResumeUploadService {

    private static final Logger log = LoggerFactory.getLogger(ResumeUploadService.class);
    private static final String UPLOAD_FOLDER = "UPLOADED";

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

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    @ResponseBody
    public Map list() {
        log.debug("uploadGet called");
        Map<String, Object> files = new HashMap<>();
        files.put("files", new ArrayList<>());
        log.debug("Returning: {}", files);
        return files;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public
    @ResponseBody
    Map upload(MultipartHttpServletRequest request) {
        log.debug("uploadPost called");
        Iterator<String> itr = request.getFileNames();
        MultipartFile mpf;
        List<Object> list = new LinkedList<>();

        while (itr.hasNext()) {
            mpf = request.getFile(itr.next());
            log.debug("Uploading {}", mpf.getOriginalFilename());


            try {
                byte[] bytes = mpf.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File("uploaded/" + mpf.getOriginalFilename())));
                stream.write(bytes);
                stream.close();
            } catch (IOException e) {
                log.error("Could not upload file {}", mpf.getOriginalFilename(), e);
                log.debug("Stacktrace {}", e);
            }

        }

        Map<String, Object> files = new HashMap<>();
        files.put("files", list);
        return files;
    }


}
