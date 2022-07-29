package com.example.numberBaseball.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GameController {
	
	@RequestMapping("/game")
	public ModelAndView chat(@RequestParam(value="targetUser", defaultValue="test")String targetUser) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("targetUser", targetUser);
		mv.setViewName("game");
		return mv;
	}
}