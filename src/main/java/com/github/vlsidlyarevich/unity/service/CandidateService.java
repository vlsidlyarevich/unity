package com.github.vlsidlyarevich.unity.service;

import com.github.vlsidlyarevich.unity.dto.CandidateDTO;
import com.github.vlsidlyarevich.unity.model.Candidate;
import com.github.vlsidlyarevich.unity.service.common.BaseRelationshipService;


public interface CandidateService extends BaseRelationshipService<Candidate, CandidateDTO> {

    String deleteImage(String id1, String id2);

}
