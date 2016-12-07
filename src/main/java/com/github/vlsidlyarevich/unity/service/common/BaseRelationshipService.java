package com.github.vlsidlyarevich.unity.service.common;

import com.github.vlsidlyarevich.unity.model.BaseEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


public interface BaseRelationshipService<T extends BaseEntity, S extends Serializable> {

    T create(String id, S object);

    T find(String id1, String id2);

    List<T> findAll();

    List<T> findAll(String id);

    T update(String id1, String id2, S object);

    String delete(String id1, String id2);

    Integer deleteQuery(String id1, Map<String, String> ids);

    Integer deleteAll(String id);
}
