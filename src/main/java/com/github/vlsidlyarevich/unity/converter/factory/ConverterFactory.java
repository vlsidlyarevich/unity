package com.github.vlsidlyarevich.unity.converter.factory;

import com.github.vlsidlyarevich.unity.converter.dto.CandidateDtoConverter;
import com.github.vlsidlyarevich.unity.converter.dto.VacancyDtoConverter;
import com.github.vlsidlyarevich.unity.converter.dto.WorkerProfileDtoConverter;
import com.github.vlsidlyarevich.unity.dto.CandidateDTO;
import com.github.vlsidlyarevich.unity.dto.VacancyDTO;
import com.github.vlsidlyarevich.unity.dto.WorkerProfileDTO;
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
        converters.put(CandidateDTO.class, new CandidateDtoConverter());
        converters.put(WorkerProfileDTO.class, new WorkerProfileDtoConverter());
        converters.put(VacancyDTO.class, new VacancyDtoConverter());
    }

    public Converter getConverter(Object type) {
        return converters.get(type);
    }
}
