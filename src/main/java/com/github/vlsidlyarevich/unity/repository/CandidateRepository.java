package com.github.vlsidlyarevich.unity.repository;

import com.github.vlsidlyarevich.unity.model.Candidate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CandidateRepository extends MongoRepository<Candidate, String> {

}
