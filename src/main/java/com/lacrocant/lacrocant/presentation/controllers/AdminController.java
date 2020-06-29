package com.lacrocant.lacrocant.presentation.controllers;

import javax.validation.Valid;

import com.lacrocant.lacrocant.application.AdminApplication;
import com.lacrocant.lacrocant.domain.admin.Admin;
import com.lacrocant.lacrocant.util.LaCrocanteException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
            return "redirect:/admin/create";
        }
    }

    @GetMapping("/") //lista todos os administradores
    public String list(ModelMap model) {
        model.addAttribute("administradores", adminApplication.list());
        return "panelAdmin/admin/list";
    }

    @GetMapping("/{id}")
    public String preEditar(@PathVariable("id") String id, ModelMap model) {
        System.out.println("AQUI ESTÁ O MELIANTE " + id);
        model.addAttribute("admin", adminApplication.findById(id));
        System.out.println("MELIANTE ENCONTRADO " + id);
        return "panelAdmin/admin/edit";
    }

    @PostMapping("/update")
    public String update(@Valid Admin admin, RedirectAttributes att) {
        try {
            adminApplication.update(admin);
            System.out.println(">>>>>>>>>>>>>>>Passou aqui");
            att.addFlashAttribute("s_message",
                    "Administrador(a) " + admin.getUserName() + " atualizado(a) com sucesso!");
        } catch (LaCrocanteException e) {
            System.out.println(">>>>>>>>Entrou no erro");
            att.addFlashAttribute("f_messages", e.getMessages());
            att.addFlashAttribute("admin", admin);
            return "panelAdmin/admin/edit" + admin.getId();
        }
        return "redirect:/admin/";
    }
    @GetMapping("/{id}/block-unblock")
	public String blockOrUnblock(@PathVariable("id") String id, RedirectAttributes att, ModelMap model,
			Authentication authentication) throws LaCrocanteException {
		try {
			final Admin loggedAdmin = adminApplication.findByUserName(authentication.getName());
			final Admin admin = adminApplication.blockOrUnblock(id, loggedAdmin.getId());
			String status = admin.getActive() ? "ativado" : "bloqueado";
			att.addFlashAttribute("s_message", "Administrador " + admin.getUserName() + " " + status + " com sucesso!");
		} catch (LaCrocanteException e) {
			att.addFlashAttribute("f_messages", e.getMessages());
			return "redirect:/admin/" + id;
		}
		return "redirect:/admin/";
    }
    @GetMapping("/user")
	public String userLog(Admin admin, ModelMap model, Authentication authentication) {
		model.addAttribute("admin", adminApplication.findByUserName(authentication.getName()));
		return "redirect:/admin/";
	}
}