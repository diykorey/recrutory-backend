package com.kandidato.config;

import com.kandidato.model.mapper.dozer.listener.UserAwareDozerListener;
import org.dozer.DozerBeanMapper;
import org.dozer.DozerEventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Spring context for ModelMapper.
 * <p/>
 * Created by andriy on 10/10/14.
 */
@Configuration
public class MapperConfig {

    public static final String MAPPINGS_XML = "com/kandidato/model/mapper/mappings.xml";

    @Autowired
    private UserAwareDozerListener dozerListener;

    @Bean
    public DozerBeanMapper beanMapperFactory() {
        DozerBeanMapper dozer = new DozerBeanMapper();
        setMappingFiles(dozer);
        setEventListeners(dozer);
        return dozer;
    }

    private DozerBeanMapper setEventListeners(DozerBeanMapper dozer) {
        List<DozerEventListener> listeners = new ArrayList<>(1);
        listeners.add(dozerListener);
        dozer.setEventListeners(listeners);
        return dozer;
    }

    private DozerBeanMapper setMappingFiles(DozerBeanMapper dozer) {
        List<String> mappingFiles = new ArrayList<>(1);
        mappingFiles.add(MAPPINGS_XML);
        dozer.setMappingFiles(mappingFiles);
        return dozer;
    }

}
