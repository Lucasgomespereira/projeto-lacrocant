package com.lacrocant.lacrocant.presentation.controllers;

import com.lacrocant.lacrocant.application.AdminApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("auth")
public class AuthenticationController {

    /* @Autowired
    private AdminApplication adminApplication; */ //será usado quando for recuperar acesso

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }
    
}