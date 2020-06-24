package com.lacrocant.lacrocant.application;

import java.util.List;

import com.lacrocant.lacrocant.domain.AbstractEntity;

public interface BaseApplication<E extends AbstractEntity, I>{
    E save(E entity);

    E update(E entity);

    E findById(I id);

    List<E> list();
    
}