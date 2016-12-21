package com.github.vlsidlyarevich.unity.db.service.common;

import com.github.vlsidlyarevich.unity.db.model.BaseEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


public interface BaseService<T extends BaseEntity> {

    T create(T object);

    T find(String id);

    List<T> findAll();

    T update(String id, T object);

    String delete(String id);

    Integer deleteQuery(Map<String, String> ids);

    Integer deleteAll();
}
