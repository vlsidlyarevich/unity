package com.github.vlsidlyarevich.unity.service;

import com.github.vlsidlyarevich.unity.model.BaseEntity;

import java.io.Serializable;
import java.util.List;


public interface BaseService<T extends BaseEntity, S extends Serializable> {

    T create(S object);

    T find(String id);

    List<T> findAll();

    String update(String id, S object);

    String delete(String id);
}
