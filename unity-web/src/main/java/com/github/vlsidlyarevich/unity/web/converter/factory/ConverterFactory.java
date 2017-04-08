package com.github.vlsidlyarevich.unity.web.converter.factory;

import com.github.vlsidlyarevich.unity.web.converter.dto.UserDTOConverter;
import com.github.vlsidlyarevich.unity.web.dto.UserDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class ConverterFactory {

    private Map<Object, Converter> converters;

    public void ConverterFactory() {

    }

    @PostConstruct
    public void init() {
        converters = new HashMap<>();
        converters.put(UserDTO.class, new UserDTOConverter());
    }

    public Converter getConverter(Object type) {
        return converters.get(type);
    }
}
