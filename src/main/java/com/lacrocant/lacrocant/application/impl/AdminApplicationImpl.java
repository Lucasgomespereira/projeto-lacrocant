package com.lacrocant.lacrocant.application.impl;

import java.util.ArrayList;
import java.util.List;

import com.lacrocant.lacrocant.application.AdminApplication;
import com.lacrocant.lacrocant.domain.admin.Admin;
import com.lacrocant.lacrocant.infra.repositories.AdminRepository;
import com.lacrocant.lacrocant.util.LaCrocanteException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminApplicationImpl implements AdminApplication {

    @Autowired
    private AdminRepository adminRep;

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
    
       /*  admin.setActive(admin.getActive()); */
        validate(admin);
        /* admin.setPassword(new BCryptPasswordEncoder().encode(admin.getPassword())); */
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

    @Override
    public Admin blockOrUnblock(String id, String loggedAdmin) throws LaCrocanteException {
        final Admin admin = findById(id);
        if (loggedAdmin == admin.getId()) {
            throw new LaCrocanteException(403, "Você não pode bloquear a si mesmo!");
        }
        admin.setActive(!admin.getActive());
        return adminRep.save(admin);
    }

}
