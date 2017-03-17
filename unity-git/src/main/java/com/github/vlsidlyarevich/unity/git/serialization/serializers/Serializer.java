package com.github.vlsidlyarevich.unity.git.serialization.serializers;


public interface Serializer<T> {

    T serialize(String json);
}
