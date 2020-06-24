package com.lacrocant.lacrocant.domain;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;

import lombok.Data;

@SuppressWarnings("serial")
@MappedSuperclass
@Data
public abstract class AbstractEntity implements Serializable {

    @Id
    private String id;
    
    protected String generateUUID() {
        return UUID.randomUUID().toString();
    }
    @PrePersist
    public void prePersist() {
        this.id = generateUUID();
    }

}