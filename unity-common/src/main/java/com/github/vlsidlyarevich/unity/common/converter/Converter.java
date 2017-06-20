package com.github.vlsidlyarevich.unity.common.converter;


public interface Converter<S, T> {

    T convert(S source);

    boolean canConvert(Object source);
}
