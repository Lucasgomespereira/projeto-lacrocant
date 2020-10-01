package com.lacrocant.lacrocant.application.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.lacrocant.lacrocant.application.AdminApplication;
import com.lacrocant.lacrocant.domain.admin.Admin;
import com.lacrocant.lacrocant.infra.mailConfig.MailService;
import com.lacrocant.lacrocant.infra.repositories.AdminRepository;
import com.lacrocant.lacrocant.infra.security.GeneratePassword;
import com.lacrocant.lacrocant.util.LaCrocanteException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminApplicationImpl implements AdminApplication {

    @Autowired
    private AdminRepository adminRep;

    @Autowired
    private MailService mailService;

    @Override
    public Admin save(final Admin admin) throws LaCrocanteException {
        validate(admin);
        if (adminRep.findByUserName(admin.getUserName()) != null) {
            throw new LaCrocanteException(409, "Nome de usuário já utilizado: " + admin.getUserName());
        }
        admin.setPassword(new BCryptPasswordEncoder().encode(admin.getPassword()));
        return adminRep.save(admin);
    }

    @Override
    public Admin findById(String id) {
        return adminRep.findById(id).get();
    }

    @Override
    public Admin update(final Admin admin) throws LaCrocanteException {
        validate(admin);
        return adminRep.save(admin);
    }

    @Override
    public List<Admin> list() {
        return adminRep.findAll();
    }

    @Override
    public Admin findByUserName(final String userName) {
        return adminRep.findByUserName(userName);
    }

    @Override
    public Admin blockOrUnblock(String id, String loggedAdmin) throws LaCrocanteException {
        final Admin admin = findById(id);
        if (loggedAdmin == admin.getId()) {
            throw new LaCrocanteException(403, "Você não pode bloquear a si mesmo!");
        }
        admin.setActive(!admin.getActive());
        return adminRep.save(admin);
    }

    // Método para recuoeração de acesso
    @Override
    public Admin recoverAccess(String userName) throws LaCrocanteException, IOException {
        if (userName == null) {
            throw new LaCrocanteException(400, "Informe o nome de usuário!");
        }
        Admin admin = adminRep.findByUserName(userName);
        if (admin == null) {
            throw new LaCrocanteException(404, "Usuário não encontrado!");
        } else {
            final String name = admin.getUserName();
            final String email = admin.getEmail();
            final String newPassword = GeneratePassword.createNewPassword();
            admin.setPassword(new BCryptPasswordEncoder().encode(newPassword));
            mailService.sendNewPassword(email, name, newPassword);
            return adminRep.save(admin);
        }

    }


    /*  Metodo para validação de dados antes da persistencia no banco de dados */
    private void validate(final Admin admin) throws LaCrocanteException {
        final List<String> messages = new ArrayList<>();
        if (admin.getFullName() == null || admin.getFullName().isEmpty()) {
            messages.add("O nome deve ser preenchido");
        }
        if (admin.getEmail() == null || admin.getEmail().isEmpty()) {
            messages.add("O e-mail deve ser preenchido");
        }
        if (admin.getUserName() == null || admin.getUserName().isEmpty()) {
            messages.add("O nome de usuário deve ser preenchido");
        }
        if (admin.getPassword() == null || admin.getPassword().isEmpty()) {
            messages.add("A senha deve ser preenchida");
        } else {
            final String pwd = admin.getPassword();
            if (pwd.length() < 6) {
                messages.add("A senha deve ter no mínimo 6 caracteres");
            }
        }
        if (!messages.isEmpty()) {
            throw new LaCrocanteException(400, messages);
        }
    }


}
