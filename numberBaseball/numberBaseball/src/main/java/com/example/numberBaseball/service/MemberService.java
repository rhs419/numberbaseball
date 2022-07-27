package com.example.numberBaseball.service;
import com.example.numberBaseball.dao.MemberMapper;
import com.example.numberBaseball.model.Member;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService{
    SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:sss");
    Date time = new Date();
    String localTime = format.format(time);

    @Autowired
    private final MemberMapper userMapper;

    @Autowired
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public void joinUser(Member member){
        member.setUserPw(passwordEncoder.encode(member.getPassword()));
        member.setUserAuth("USER");
        userMapper.saveUser(member);
    }

    @Override
    public Member loadUserByUsername(String userId) throws UsernameNotFoundException {
        //여기서 받은 유저 패스워드와 비교하여 로그인 인증
        Member user = userMapper.getUserAccount(userId);
        if (user == null){
            throw new UsernameNotFoundException("User not authorized.");
        }
        return user;
    }
}