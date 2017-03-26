package com.github.vlsidlyarevich.unity.web.converter.dto;

import com.github.vlsidlyarevich.unity.web.dto.CandidateDTO;
import com.github.vlsidlyarevich.unity.db.model.Candidate;
import com.github.vlsidlyarevich.unity.db.model.Name;
import org.springframework.core.convert.converter.Converter;

public class CandidateDTOConverter implements Converter<CandidateDTO, Candidate> {

    @Override
    public Candidate convert(CandidateDTO dto) {
        Candidate candidate = new Candidate();
        candidate.setName(dto.getName() != null ? dto.getName() : (new Name()));
        candidate.setAge(dto.getAge());
        candidate.setGender(dto.getGender());
        candidate.setImageId(dto.getImageId());
        candidate.setBirthday(dto.getBirthday() != null ? dto.getBirthday() : "");
        candidate.setSkype(dto.getSkype() != null ? dto.getSkype() : "");
        candidate.setHrSkype(dto.getHrSkype() != null ? dto.getHrSkype() : "");
        candidate.setGithubUrl(dto.getGithubUrl() != null ? dto.getGithubUrl() : "");
        candidate.setLinkedInUrl(dto.getLinkedInUrl() != null ? dto.getLinkedInUrl() : "");

        return candidate;
    }
}
