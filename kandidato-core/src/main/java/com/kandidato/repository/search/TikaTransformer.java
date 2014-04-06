package com.kandidato.repository.search;


import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Component
public class TikaTransformer {

    public String textFrom(byte[] bytes){

        Tika tika = new Tika();
        try(InputStream is = new ByteArrayInputStream(bytes)){
            return tika.parseToString(is);
        } catch (IOException | TikaException e) {
            e.printStackTrace();
            return "";
        }
    }
}
