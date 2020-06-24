package com.lacrocant.lacrocant.domain.admin;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
public class admin {

    @Id
    private String id;

    private String fullName;

    private String userName;

    private String password;

    private String email;

    private Boolean active = true;
}