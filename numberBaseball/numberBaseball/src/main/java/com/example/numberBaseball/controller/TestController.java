package com.example.numberBaseball.controller;

import org.springframework.web.bind.annotation.GetMapping;

import com.example.numberBaseball.model.Test;
import com.example.numberBaseball.service.TestService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    private TestService testService;
    public TestController(TestService testService){
        this.testService=testService;
    }

    @GetMapping("/test")
    public String test(Model model){
        List<Test> allTest = testService.getAllTest();
        model.addAttribute("testList", allTest);
        model.addAttribute("aaa", "aaabbb");
        logger.info(allTest.toString());
        return "test";
    }

    @GetMapping("/admin/test")
    public String testAdmin(Model model){
        List<Test> allTest = testService.getAllTest();
        model.addAttribute("testList", allTest);
        model.addAttribute("aaa", "Admin");
        logger.info(allTest.toString());
        return "test";
    }

}
