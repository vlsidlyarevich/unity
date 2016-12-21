package com.github.vlsidlyarevich.unity.db.service;

import com.github.vlsidlyarevich.unity.db.model.Candidate;
import com.github.vlsidlyarevich.unity.db.service.common.BaseRelationshipService;


public interface CandidateService extends BaseRelationshipService<Candidate> {

    String deleteImage(String id1, String id2);

}
