package com.example.numberBaseball.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
    @RequestMapping("/")
	public ModelAndView chat() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index");
		return mv;
    }
}
