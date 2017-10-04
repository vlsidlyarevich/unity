package com.github.vlsidlyarevich.unity.mapping.converter;

import org.dozer.CustomConverter;

public class TwitterUserUrlCustomConverter implements CustomConverter {

    @Override
    public Object convert(final Object url, final Object userUrl,
                          final Class<?> aClass, final Class<?> aClass1) {
        return userUrl;
    }
}
