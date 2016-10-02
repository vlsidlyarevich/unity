package com.github.vlsidlyarevich.unity.service;

import com.github.vlsidlyarevich.unity.models.WorkerProfile;
import com.github.vlsidlyarevich.unity.repository.WorkerProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.util.List;

/**
 * Created by vlad on 02.10.16.
 */
@Service
public class WorkerProfileSearchService {

    @Autowired
    private WorkerProfileRepository repository;

    @Autowired
    private MongoOperations mongoOperations;

    public List<WorkerProfile> findByFilters(MultiValueMap<String, String> filters) {
        Query query = new Query();

        return mongoOperations.find(query, WorkerProfile.class);
    }

}
