package com.example.numberBaseball.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

	List<String> userList = new ArrayList<>();

    @RequestMapping("/")
	public ModelAndView chat() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
		UserDetails userDetails = (UserDetails)principal; 
		String username = userDetails.getUsername();
		if(userList.indexOf(username)==-1)
			userList.add(username);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index");
		return mv;
    }
}
