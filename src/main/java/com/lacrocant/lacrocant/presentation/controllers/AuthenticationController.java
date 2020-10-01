package com.lacrocant.lacrocant.presentation.controllers;

import java.io.IOException;

import com.lacrocant.lacrocant.application.AdminApplication;
import com.lacrocant.lacrocant.domain.admin.Admin;
import com.lacrocant.lacrocant.util.LaCrocanteException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AdminApplication adminApplication; //será usado quando for recuperar acesso

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/recover")
    public String recoverAccess() {
        return "auth/recover-access";
    }

    @PostMapping("/recover-access")
    public String recoverAccess(@RequestParam(value = "userName", required = false) String userName,
            RedirectAttributes att) throws IOException {
        try {
            Admin admin = adminApplication.recoverAccess(userName);
            return "redirect:/auth/login?email=" + admin.getEmail();
        } catch (LaCrocanteException e) {
            if (e.getStatusCode() == 404 || e.getStatusCode() == 400) {
                att.addFlashAttribute("f_message", e.getMessages().get(0));
                e.printStackTrace();
            } else {
                att.addFlashAttribute("f_message", "Falha na execução!");
                e.printStackTrace();
            }
            return "redirect:/auth/recover?error=true";
        }
    }
    
}