package com.github.vlsidlyarevich.unity.web.repository;

import com.github.vlsidlyarevich.unity.web.model.Candidate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CandidateRepository extends MongoRepository<Candidate, String> {

}
