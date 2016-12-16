package com.github.vlsidlyarevich.unity.services.db.service;

import com.github.vlsidlyarevich.unity.web.dto.CandidateDTO;
import com.github.vlsidlyarevich.unity.db.model.model.Candidate;
import com.github.vlsidlyarevich.unity.services.db.service.common.BaseRelationshipService;


public interface CandidateService extends BaseRelationshipService<Candidate, CandidateDTO> {

    String deleteImage(String id1, String id2);

}
