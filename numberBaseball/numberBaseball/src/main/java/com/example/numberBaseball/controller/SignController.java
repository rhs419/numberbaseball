package com.example.numberBaseball.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.numberBaseball.model.Member;
import com.example.numberBaseball.service.MemberService;

@Controller
public class SignController {
    @Autowired
    private MemberService userService;

    /**
     * 로그인 폼
     * @return
     */
    @GetMapping("/login")
    public String login(){
        return "sign/signin";
    }

    /**
     * 회원가입 폼
     * @return
     */
    @GetMapping("/signUp")
    public String signUpForm() {
        return "sign/signup";
    }

    /**
     * 로그인 실패 폼
     * @return
     */
    @GetMapping("/access_denied")
    public String accessDenied() {
        return "user/access_denied";
    }

    /**
     * 회원가입 진행
     * @param user
     * @return
     */
    @PostMapping("/signUp")
    public String signUp(Member user) {
        System.out.println("회원가입");
        userService.joinUser(user);
        return "redirect:login";
    }

    /**
     * 유저 페이지
     * @param model
     * @param authentication
     * @return
     */
    @GetMapping("/user_access")
    public String userAccess(Model model, Authentication authentication) {
        //Authentication 객체를 통해 유저 정보를 가져올 수 있다.
        Member user = (Member) authentication.getPrincipal();  //userDetail 객체를 가져옴
        model.addAttribute("info", user.getUserId() +"의 "+ user.getUserName()+ "님");      //유저 아이디
        return "redirect:/";
    }

}
