package com.lacrocant.lacrocant.infra.repositories;

import com.lacrocant.lacrocant.domain.admin.Admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {
    Admin findByUserName(String userName);

}