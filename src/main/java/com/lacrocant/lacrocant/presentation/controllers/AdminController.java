package com.lacrocant.lacrocant.presentation.controllers;

import com.lacrocant.lacrocant.domain.admin.Admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("admin")
public class AdminController {

    @GetMapping("create")
    public String createAdmin() {
        return "/panelAdmin/create";
    }

    @PostMapping("/saveAdmin")
    public String save(@ModelAttribute("admin") String admin, Admin admin2, RedirectAttributes attr) {
        return null;
    }
}