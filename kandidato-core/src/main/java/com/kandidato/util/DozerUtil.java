package com.kandidato.util;


import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DozerUtil {

    @Autowired
    private DozerBeanMapper dozer;

    public <E> List<E> map(List<?> list, Class<E> clazz){
        List<E> out = new ArrayList<>(list.size());

        for (Object item : list){
            out.add(dozer.map(item, clazz));
        }

        return out;
    }



    public <E> E map(Object obj, Class<E> clazz){
        return dozer.map(obj, clazz);
    }
}
