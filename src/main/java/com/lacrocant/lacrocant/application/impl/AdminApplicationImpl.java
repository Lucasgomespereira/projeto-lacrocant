package com.lacrocant.lacrocant.application.impl;

import java.util.List;

import com.lacrocant.lacrocant.application.AdminApplication;
import com.lacrocant.lacrocant.domain.admin.Admin;
import com.lacrocant.lacrocant.infra.repositories.AdminRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class AdminApplicationImpl implements AdminApplication {
    
    @Autowired
    private AdminRepository adminRep;

    @Override
    public Admin save(Admin admin) {
        return adminRep.save(admin);
    }

    @Override
    public Admin update(Admin admin) {
        return adminRep.save(admin);
    }

    @Override
    public Admin findById(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Admin> list() {
        return adminRep.findAll();
    }

    @Override
    public Admin findByUserName(String userName) {
        return adminRep.findByUserName(userName);
    }

    
}
    
