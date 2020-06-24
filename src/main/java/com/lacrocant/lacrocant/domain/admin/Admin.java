package com.lacrocant.lacrocant.domain.admin;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity(name = "TB_ADMIN")
@Data
@AllArgsConstructor
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    private String fullName;

    private String userName;

    private String password;

    private String email;

    private Boolean active = true;
}