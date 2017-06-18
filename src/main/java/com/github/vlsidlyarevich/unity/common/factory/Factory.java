package com.github.vlsidlyarevich.unity.common.factory;


public interface Factory<M> {

    M getObject();

    Class<M> getObjectType();

    boolean isSingleton();
}
