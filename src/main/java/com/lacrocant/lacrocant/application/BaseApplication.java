package com.lacrocant.lacrocant.application;

import java.util.List;

import com.lacrocant.lacrocant.domain.AbstractEntity;
import com.lacrocant.lacrocant.util.LaCrocanteException;

public interface BaseApplication<E extends AbstractEntity, I>{
    
    E save(E entity) throws LaCrocanteException;

    E update(E entity) throws LaCrocanteException;

    E findById(I id) throws LaCrocanteException;

    List<E> list();
    
}