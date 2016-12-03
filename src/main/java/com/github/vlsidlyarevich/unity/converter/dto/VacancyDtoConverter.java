package com.github.vlsidlyarevich.unity.converter.dto;

import com.github.vlsidlyarevich.unity.dto.VacancyDTO;
import com.github.vlsidlyarevich.unity.model.JobType;
import com.github.vlsidlyarevich.unity.model.Speciality;
import com.github.vlsidlyarevich.unity.model.Vacancy;
import org.springframework.core.convert.converter.Converter;


public class VacancyDtoConverter implements Converter<VacancyDTO, Vacancy> {

    @Override
    public Vacancy convert(VacancyDTO dto) {
        Vacancy vacancy = new Vacancy();
        vacancy.setSpeciality(dto.getSpeciality() != null ? dto.getSpeciality() : Speciality.UNKNOWN);
        vacancy.setJobType(dto.getJobType() != null ? dto.getJobType() : JobType.UNKNOWN);
        vacancy.setLocation(dto.getLocation() != null ? dto.getLocation() : "");
        vacancy.setSalary(dto.getSalary() != null ? dto.getSalary() : "");
        vacancy.setDescription(dto.getDescription() != null ? dto.getDescription() : "");

        return vacancy;
    }
}
