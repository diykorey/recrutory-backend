package com.kandidato.util;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CommonController {
  @RequestMapping("/{name}")
  public ModelAndView myView(@PathVariable("name") String name) {
    return new ModelAndView(name);
  }
}
