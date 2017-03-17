package com.github.vlsidlyarevich.unity.git.serialization.desirializers;


public interface Desirializer <T> {

    String desirialize(T object);
}
