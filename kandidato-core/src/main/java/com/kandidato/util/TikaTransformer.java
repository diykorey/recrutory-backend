package com.kandidato.util;


import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Component
public class TikaTransformer {

    private static final Logger LOG = LoggerFactory.getLogger(TikaTransformer.class);

    private final Tika tika;

    public TikaTransformer() {
        this.tika = new Tika();
    }

    public String textFrom(byte[] bytes){
        try(InputStream inputStream = new ByteArrayInputStream(bytes)){
            return tika.parseToString(inputStream);
        } catch (IOException | TikaException e) {
            LOG.warn("Error parsing byte[] to text", e);
            return "";
        }
    }
}
