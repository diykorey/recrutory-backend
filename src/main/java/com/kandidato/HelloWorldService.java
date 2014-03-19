package com.kandidato;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Simple service that can be used in following format http://localhost:8080/hello?name=Mykola
 */
@Controller
public class HelloWorldService{
    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello( @RequestParam(value="name", required=false, defaultValue="World") String name) {
        return "Hello " + name;
    }
}