package com.github.vlsidlyarevich.unity.service;

import com.github.vlsidlyarevich.unity.models.WorkerProfile;
import com.github.vlsidlyarevich.unity.repository.WorkerProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        for (Map.Entry<String, String> filter : prepareParameters(filters).entrySet()) {
            switch (filter.getKey()) {
                case "firstname": {
                    query.addCriteria(Criteria.where("name.firstName").is(filter.getValue()));
                    break;
                }
                case "secondname": {
                    query.addCriteria(Criteria.where("name.secondName").is(filter.getValue()));
                    break;
                }
                case "age": {
                    query.addCriteria(Criteria.where("age").is(filter.getValue()));
                    break;
                }
                case "gender": {
                    query.addCriteria(Criteria.where("gender").is(filter.getValue()));
                    break;
                }
                case "speciality": {
                    query.addCriteria(Criteria.where("speciality").is(filter.getValue()));
                    break;
                }
                case "skype": {
                    query.addCriteria(Criteria.where("skype").is(filter.getValue()));
                    break;
                }
            }
        }
        return mongoOperations.find(query, WorkerProfile.class);
    }

    private Map<String, String> prepareParameters(MultiValueMap<String, String> filters) {
        Map<String, String> parameters = new HashMap<String, String>();

        for (String theKey : filters.keySet()) {
            parameters.put(theKey, filters.getFirst(theKey));
        }
        return parameters;
    }

}
