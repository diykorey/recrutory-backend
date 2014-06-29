package com.kandidato.service.comment;

import com.kandidato.persistence.entity.Vacancy;
import com.kandidato.persistence.entity.comment.VacancyComment;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * Created by andriy on 6/9/14.
 */
public class CommentServiceTest {

//    @Test
    public void shouldPostAndFetchVacancyComment() {

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

            String vacJson = "{\"state\":\"OPEN\",\"hot\":false,\"requirements\":\"Test vacancy\",\"project\":{\"id\":1,\"name\":\"Test project\",\"description\":\"Test project name\",\"creationTime\":1399150800000},\"creator\":{\"id\":1}}";
            HttpEntity<String> requestEntity = new HttpEntity<String>(vacJson, headers);

            RestTemplate template = new RestTemplate();
            ResponseEntity<Vacancy> postResponse = template.postForEntity("http://localhost:8080/vacancy", requestEntity, Vacancy.class);
            Assert.assertEquals(HttpStatus.CREATED, postResponse.getStatusCode());
            long id = postResponse.getBody().getId();

            String commentJson = "{\"id\":2,\"comment\":\"Unit testing rocks\",\"creationDate\":1402261200000,\"creator\":{\"id\":1}";
            HttpEntity<String> commentRequestEntity = new HttpEntity<String>(commentJson, headers);
            RestTemplate commentTemplate = new RestTemplate();
            ResponseEntity<VacancyComment> commentPostResponse = commentTemplate.postForEntity("http://localhost:8080/comment/VACANCY/" + id, commentRequestEntity, VacancyComment.class);
            Assert.assertEquals(HttpStatus.CREATED, commentPostResponse.getStatusCode());

            ResponseEntity<VacancyComment> entity = template.getForEntity("http://localhost:8080/comment/VACANCY/" + id, VacancyComment.class);
            Assert.assertEquals(HttpStatus.OK, entity.getStatusCode());
            Assert.assertEquals(id, (long) entity.getBody().getEntity().getId());
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


}
