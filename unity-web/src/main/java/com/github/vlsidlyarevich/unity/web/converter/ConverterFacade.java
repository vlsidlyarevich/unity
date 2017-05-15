package com.github.vlsidlyarevich.unity.web.converter;

import com.github.vlsidlyarevich.unity.common.converter.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
public class ConverterFacade {

    @Autowired
    private List<Converter> converters;

    public Object convert(Serializable object) {
        return converters.stream()
                .filter(converter -> converter.canConvert(object))
                .findFirst()
                .get()
                .convert(object);
    }
}
