package com.example.numberBaseball.command;

import lombok.Data;

@Data
public class RegisterForm {
    private String id;
    private String password;
    private String pwCheck;
    private String name;
}