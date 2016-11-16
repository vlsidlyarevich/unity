package com.github.vlsidlyarevich.unity.service;

import com.github.vlsidlyarevich.unity.model.BaseEntityNeo4j;

import java.io.Serializable;
import java.util.List;

//TODO:Remove after integration of neo4j
public interface BaseServiceNeo4j<T extends BaseEntityNeo4j, S extends Serializable> {

    T create(S object);

    T find(String id);

    List<T> findAll();

    String update(String id, S object);

    String delete(String id);
}
