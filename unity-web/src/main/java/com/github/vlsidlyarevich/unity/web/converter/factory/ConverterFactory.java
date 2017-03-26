package com.github.vlsidlyarevich.unity.web.converter.factory;

import com.github.vlsidlyarevich.unity.web.converter.dto.CandidateDTOConverter;
import com.github.vlsidlyarevich.unity.web.converter.dto.UserDTOConverter;
import com.github.vlsidlyarevich.unity.web.converter.dto.VacancyDTOConverter;
import com.github.vlsidlyarevich.unity.web.converter.dto.WorkerProfileDTOConverter;
import com.github.vlsidlyarevich.unity.web.dto.CandidateDTO;
import com.github.vlsidlyarevich.unity.web.dto.UserDTO;
import com.github.vlsidlyarevich.unity.web.dto.VacancyDTO;
import com.github.vlsidlyarevich.unity.web.dto.WorkerProfileDTO;
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
        converters.put(CandidateDTO.class, new CandidateDTOConverter());
        converters.put(WorkerProfileDTO.class, new WorkerProfileDTOConverter());
        converters.put(VacancyDTO.class, new VacancyDTOConverter());
        converters.put(UserDTO.class, new UserDTOConverter());
    }

    public Converter getConverter(Object type) {
        return converters.get(type);
    }
}
