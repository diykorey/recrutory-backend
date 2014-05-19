package com.kandidato.service.vacancy;

import com.kandidato.persistence.entity.Vacancy;
import com.sun.syndication.io.impl.Base64;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Date;

/**
 * Created by andriy on 5/4/14.
 */
public class VacancyServiceTest {
    static HttpHeaders getHeaders(String auth) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        byte[] encodedAuthorisation = Base64.encode(auth.getBytes());
        headers.add("Authorization", "Basic " + new String(encodedAuthorisation));

        return headers;
    }

    @Test
    @Ignore
    public void shouldPostAndFetchVacancy() {

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

//            Vacancy vac = getTestVacancy(id);
//            ObjectMapper mapper = new ObjectMapper();
//            mapper.writeValueAsString(vac);
            String vacJson = "{\"state\":\"OPEN\",\"hot\":false,\"requirements\":\"Test vacancy\",\"project\":{\"id\":1,\"name\":\"Test project\",\"description\":\"Test project name\",\"creationTime\":1399150800000},\"creator\":{\"id\":1},\"createTime\":1399150800000}";

            HttpEntity<String> requestEntity = new HttpEntity<String>(vacJson, headers);

            RestTemplate template = new RestTemplate();
            ResponseEntity<Vacancy> postResponse = template.postForEntity("http://localhost:8080/vacancy", requestEntity, Vacancy.class);
            Assert.assertEquals(HttpStatus.CREATED, postResponse.getStatusCode());
            long id = postResponse.getBody().getId();


            ResponseEntity<Vacancy> entity = template.getForEntity("http://localhost:8080/vacancy/" + id, Vacancy.class);
            Assert.assertEquals(HttpStatus.OK, entity.getStatusCode());
            Assert.assertEquals(id, (long) entity.getBody().getId());
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private Vacancy getTestVacancy(long id) {
        Vacancy vacancy = new Vacancy();
        vacancy.setId(id);
        vacancy.setCreateTime(new Date());
        return vacancy;
    }
}
