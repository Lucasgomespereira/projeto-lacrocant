package com.lacrocant.lacrocant.domain.admin;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lacrocant.lacrocant.domain.AbstractEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@SuppressWarnings("serial")
@Entity
@Data
@AllArgsConstructor
public class Admin extends AbstractEntity {

    @NonNull
    private String fullName;

    @Column(unique = true)
    private String userName;

    @JsonIgnore
    private String password;

    @Column(unique = true)
    private String email;

    private Boolean active = true;
}