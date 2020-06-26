package com.lacrocant.lacrocant.application.impl;

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
        if (adminRep.findByUserName(admin.getUserName()) != null) {
            throw new LaCrocanteException(409, "Nome de usuário já utilizado: " + admin.getUserName());
        }
        admin.setPassword(new BCryptPasswordEncoder().encode(admin.getPassword()));
        return adminRep.save(admin);
    }

    @Override
    public Admin update(final Admin admin) {
        return adminRep.save(admin);
    }

    @Override
    public Admin findById(final String id) {
        return null;
    }

    @Override
    public List<Admin> list() {
        return adminRep.findAll();
    }

    @Override
    public Admin findByUserName(final String userName) {
        return adminRep.findByUserName(userName);
    }

    
}
    
