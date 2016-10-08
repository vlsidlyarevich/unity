package com.github.vlsidlyarevich.unity.service;

import com.github.vlsidlyarevich.unity.models.BaseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by vladislav on 10/8/16.
 */
public interface BaseService<T extends BaseEntity, S extends Serializable> {

    T create(S object);

    T find(String id);

    List<T> findAll();

    String update(String id, S object);

    String delete(String id);
}
