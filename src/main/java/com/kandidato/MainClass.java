package com.kandidato;

import com.google.appengine.repackaged.com.google.common.io.Files;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.codec.binary.Base64;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilders;

import java.io.IOException;
import java.nio.file.Paths;

public class MainClass {

    public static void main(String[] args) throws IOException {

        byte[] bytes = Files.toByteArray(Paths.get("E:/Mykola_Kavf_CV.pdf").toFile());
        String r = Base64.encodeBase64String(bytes);


        Resume resume = new Resume("application/pdf", r);
        Person person = new Person(resume);

        Client client = new TransportClient().addTransportAddress(new InetSocketTransportAddress("127.0.0.1", 9300));

        String source = new Gson().toJson(person);
        System.out.println(source);

        IndexResponse index = client.prepareIndex("resumes6", "person", "1").setSource(source).execute().actionGet();

        System.out.println(index.getId());

        client.close();
    }
}

class Person{
    private Resume my_attachment;

    Person(Resume resume) {
        this.my_attachment = resume;
    }

    public Resume getResume() {
        return my_attachment;
    }
}

class Resume{
    private String _content_type;

    private String content;

    Resume(String _content_type, String content) {
        this._content_type = _content_type;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public String get_content_type() {
        return _content_type;
    }
}