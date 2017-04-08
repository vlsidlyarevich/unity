package com.github.vlsidlyarevich.unity.web.converter;

import com.github.vlsidlyarevich.unity.db.model.User;
import com.github.vlsidlyarevich.unity.web.converter.factory.ConverterFactory;
import com.github.vlsidlyarevich.unity.web.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConverterFacade {

    @Autowired
    private ConverterFactory converterFactory;

    public User convert(UserDTO dto){
        return (User) converterFactory.getConverter(dto.getClass()).convert(dto);
    }
}
