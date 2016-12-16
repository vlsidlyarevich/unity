package com.github.vlsidlyarevich.unity.services.db.service.common;

import com.github.vlsidlyarevich.unity.db.model.model.BaseEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


public interface BaseService<T extends BaseEntity, S extends Serializable> {

    T create(S object);

    T find(String id);

    List<T> findAll();

    T update(String id, S object);

    String delete(String id);

    Integer deleteQuery(Map<String, String> ids);

    Integer deleteAll();
}
