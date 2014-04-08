package com.kandidato.service.search;

import com.kandidato.manager.search.ResumeIndexingManager;
import com.kandidato.persistence.search.ResumeSearchRepository;
import com.kandidato.util.TikaTransformer;
import com.kandidato.persistence.search.elastic.ResumeDocument;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.support.ElasticsearchRepositoryFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class SearchService {

    @Autowired
    private ResumeIndexingManager indexer;

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ResponseBody
    public String start() {
        indexer.indexResumes();
        return "Done";
    }

    public static void main(String[] args) throws IOException {
        Client client = new TransportClient().addTransportAddress(new InetSocketTransportAddress("localhost", 9300));
        ElasticsearchOperations ops = new ElasticsearchTemplate(client);

        ElasticsearchRepositoryFactory fac = new ElasticsearchRepositoryFactory(ops);

        //ResumeSearchRepository rep = new ResumeSearchRepositoryImpl(ops);
        ResumeSearchRepository rep =  fac.getRepository(ResumeSearchRepository.class);

        Path file = Paths.get("E:\\Mykola_Kavf_CV.pdf");
        byte[] data = Files.readAllBytes(file);
        TikaTransformer t = new TikaTransformer();

        rep.index(new ResumeDocument(0l, 0, 0, t.textFrom(data)));
    }
}
