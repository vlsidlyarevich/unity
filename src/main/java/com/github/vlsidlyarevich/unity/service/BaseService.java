package com.github.vlsidlyarevich.unity.service;

import com.github.vlsidlyarevich.unity.model.BaseEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


public interface BaseService<T extends BaseEntity, S extends Serializable> {

    T create(S object);

    T find(Long id);

    List<T> findAll();

    T update(Long id, S object);

    Long delete(Long id);

    Integer deleteQuery(Map<String, String> ids);

    Integer deleteAll();
}
