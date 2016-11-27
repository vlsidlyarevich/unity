package com.github.vlsidlyarevich.unity.service.common;

import com.github.vlsidlyarevich.unity.model.BaseEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


public interface BaseRelationshipService<T extends BaseEntity, S extends Serializable> {

    T create(Long id, S object);

    T find(Long id1, Long id2);

    List<T> findAll();

    List<T> findAll(Long id);

    T update(Long id1, Long id2, S object);

    Long delete(Long id1, Long id2);

    Integer deleteQuery(Map<String, String> ids);

    Integer deleteAll();
}
