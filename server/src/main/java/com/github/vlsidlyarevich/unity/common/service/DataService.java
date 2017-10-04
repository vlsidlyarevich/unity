package com.github.vlsidlyarevich.unity.common.service;

import java.util.Optional;

public interface DataService<D> {

    Optional<? extends D> getData(String username);
}
