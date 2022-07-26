package com.example.numberBaseball.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.numberBaseball.model.Test;

@Mapper
public interface TestMapper {
    @Select("SELECT test1, test2 FROM \"TestT\";")
    List<Test> findAll();
}
