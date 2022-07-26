package com.example.numberBaseball.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.numberBaseball.model.Member;

@Mapper
public interface MemberMapper {
    @Select("SELECT * FROM users WHERE userid= #{userId}")
    Member getUserAccount(@Param("userId") String userId);

    // 회원가입
    @Insert("INSERT INTO users(userid, userpw, username, userauth) VALUES(#{userId},#{userPw},#{userName},#{userAuth});")
    void saveUser(Member member);
}
