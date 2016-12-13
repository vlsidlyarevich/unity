package com.github.vlsidlyarevich.unity.web.service;

import com.github.vlsidlyarevich.unity.web.dto.CandidateDTO;
import com.github.vlsidlyarevich.unity.web.model.Candidate;
import com.github.vlsidlyarevich.unity.web.service.common.BaseRelationshipService;


public interface CandidateService extends BaseRelationshipService<Candidate, CandidateDTO> {

    String deleteImage(String id1, String id2);

}
