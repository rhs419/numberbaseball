package com.example.numberBaseball.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final SessionRegistry sessionRegistry;

	List<String> userList = new ArrayList<>();

    @RequestMapping("/")
	public ModelAndView chat() {
		List<String> loginedList = sessionRegistry.getAllPrincipals().stream().map(o->((User)o).getUsername()).toList();
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
		UserDetails userDetails = (UserDetails)principal; 
		String username = userDetails.getUsername();
		if(userList.indexOf(username)==-1)
			userList.add(username);
		ModelAndView mv = new ModelAndView();
		mv.addObject("userList", userList);
		mv.setViewName("index");
		return mv;
    }

	
}
