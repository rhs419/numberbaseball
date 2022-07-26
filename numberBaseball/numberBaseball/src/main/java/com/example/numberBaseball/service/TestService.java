package com.example.numberBaseball.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.numberBaseball.dao.TestMapper;
import com.example.numberBaseball.model.Test;

@Service
public class TestService {
    private TestMapper testMapper;

    public TestService(TestMapper testMapper){
        this.testMapper=testMapper;
    }

    public List<Test> getAllTest(){
        final List<Test> testList = testMapper.findAll();
        return testList;
    }
}
