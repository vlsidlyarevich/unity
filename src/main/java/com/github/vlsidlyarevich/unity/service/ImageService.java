package com.github.vlsidlyarevich.unity.service;

import com.github.vlsidlyarevich.unity.model.BaseEntity;

import java.util.List;

/**
 * Created by vladislav on 10/11/16.
 */
public interface ImageService<T extends BaseEntity> {

    T find(String id);

    T create(T object);

    List<T> findAll();

    String delete(String id);
}
