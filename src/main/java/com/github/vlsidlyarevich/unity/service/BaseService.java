package com.github.vlsidlyarevich.unity.service;

import com.github.vlsidlyarevich.unity.model.BaseEntity;

import java.io.Serializable;
import java.util.List;


public interface BaseService<T extends BaseEntity, S extends Serializable> {

    T create(S object);

    T find(Long id);

    //TODO:// FIXME: 17/11/16
//    List<T> findAll();

    Long update(Long id, S object);

    Long delete(Long id);
}
