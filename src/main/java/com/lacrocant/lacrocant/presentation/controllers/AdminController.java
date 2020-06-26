package com.lacrocant.lacrocant.presentation.controllers;

import javax.validation.Valid;

import com.lacrocant.lacrocant.application.AdminApplication;
import com.lacrocant.lacrocant.domain.admin.Admin;
import com.lacrocant.lacrocant.util.LaCrocanteException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private AdminApplication adminApplication;

    @GetMapping("create")
    public String createAdmin() {
        return "panelAdmin/admin/create";
    }

    @PostMapping("/save")
    public String saveAdmin( @Valid @ModelAttribute("admin") Admin admin, RedirectAttributes att) {
        try {
            adminApplication.save(admin);
            att.addFlashAttribute("s_message",
                    "Administrador(a) " + admin.getUserName() + " cadastrado(a) com sucesso!");
            return "redirect:/admin/"; // redirecionar para a dashboard
        } catch (LaCrocanteException e) {
            admin.setId(null);
            att.addFlashAttribute("f_messages", e.getMessages());
            att.addFlashAttribute("admin", admin);
            att.addFlashAttribute("content", "panelAdmin/admin/create");
            return "redirect:/admin/create";
        }
    }

    @GetMapping("/") //lista todos os administradores
    public String list(ModelMap model) {
        /* model.addAttribute("title", "Administradores");
        model.addAttribute("content", "panelAdmin/index"); */
        model.addAttribute("administradores", adminApplication.list());
        return "panelAdmin/admin/list";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") String id, ModelMap model) throws LaCrocanteException {
        model.addAttribute("admin", adminApplication.findById(id));
        /* model.addAttribute("title", "Cadastro de Administrador");
        model.addAttribute("content", "panelAdmin/edit"); */
        return "panelAdmin/admin/edit";
    }

    @PostMapping("/update")
    public String update(@Valid Admin admin, RedirectAttributes att) {
        try {
            adminApplication.update(admin);
            att.addFlashAttribute("s_message",
                    "Administrador(a) " + admin.getUserName() + " atualizado(a) com sucesso!");
        } catch (LaCrocanteException e) {
            att.addFlashAttribute("f_messages", e.getMessages());
            att.addFlashAttribute("admin", admin);
           /*  att.addFlashAttribute("content", "panelAdmin/edit"); */
            return "redirect:/panelAdmin/" + admin.getId();
        }
        return "redirect:/panelAdmin/";
    }
}