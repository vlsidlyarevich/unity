package com.github.vlsidlyarevich.unity.web.converter;

import com.github.vlsidlyarevich.unity.common.converter.Converter;
import com.github.vlsidlyarevich.unity.db.model.DbModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
public class ConverterFacade {

    @Autowired
    private List<Converter> converters;

    public DbModel convert(Serializable dto) {
        return (DbModel) converters.stream()
                .filter(converter -> converter.canConvert(dto))
                .findFirst()
                .get()
                .convert(dto);
    }
}
