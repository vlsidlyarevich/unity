package com.github.vlsidlyarevich.unity.service;

import com.github.vlsidlyarevich.unity.model.BaseEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by vladislav on 10/8/16.
 */
public interface SearchService<T extends BaseEntity> {

    List<T> find(Map<String, String> filters);
}
