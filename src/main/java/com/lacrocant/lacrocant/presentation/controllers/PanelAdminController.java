package com.lacrocant.lacrocant.presentation.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("panelAdmin")
public class PanelAdminController {
    @GetMapping({ "", "/" })
    public String index() {
        // model.addAttribute("title", "LaCrocant");
        // model.addAttribute("content", "panelAdmin/index");
        return "panelAdmin/index";
    }
}