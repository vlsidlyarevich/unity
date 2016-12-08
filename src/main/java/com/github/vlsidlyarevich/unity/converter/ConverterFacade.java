package com.github.vlsidlyarevich.unity.converter;

import com.github.vlsidlyarevich.unity.converter.factory.ConverterFactory;
import com.github.vlsidlyarevich.unity.dto.CandidateDTO;
import com.github.vlsidlyarevich.unity.dto.VacancyDTO;
import com.github.vlsidlyarevich.unity.dto.WorkerProfileDTO;
import com.github.vlsidlyarevich.unity.model.Candidate;
import com.github.vlsidlyarevich.unity.model.Vacancy;
import com.github.vlsidlyarevich.unity.model.WorkerProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ConverterFacade {

    @Autowired
    private ConverterFactory converterFactory;

    public WorkerProfile convert(WorkerProfileDTO dto) {
        return (WorkerProfile) converterFactory.getConverter(dto.getClass()).convert(dto);
    }

    public Vacancy convert(VacancyDTO dto) {
        return (Vacancy) converterFactory.getConverter(dto.getClass()).convert(dto);
    }

    public Candidate convert(CandidateDTO dto) {
        return (Candidate) converterFactory.getConverter(dto.getClass()).convert(dto);
    }
}
