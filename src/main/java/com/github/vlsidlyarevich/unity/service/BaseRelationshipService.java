package com.github.vlsidlyarevich.unity.service;

import com.github.vlsidlyarevich.unity.model.BaseEntity;

import java.io.Serializable;


public interface BaseRelationshipService<T extends BaseEntity, S extends Serializable> extends BaseService {

    T create(Long id, S object);

    Long update(Long id1, Long id2, S object);

}
