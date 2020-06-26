package com.lacrocant.lacrocant.domain.admin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lacrocant.lacrocant.domain.AbstractEntity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@SuppressWarnings("serial")
@Entity
@Data
@NoArgsConstructor
public class Admin extends AbstractEntity {

    @NotBlank(message = "Campo Obrigatório")
    private String fullName;

    @Column(unique = true)
    private String userName;

    @JsonIgnore
    private String password;

    @Column(unique = true)
    private String email;

    private Boolean active = true;

    @Enumerated(EnumType.STRING)
    private Role role;
}