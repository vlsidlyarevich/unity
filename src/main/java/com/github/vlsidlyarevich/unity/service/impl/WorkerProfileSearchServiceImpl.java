package com.github.vlsidlyarevich.unity.service.impl;

import com.github.vlsidlyarevich.unity.model.WorkerProfile;
import com.github.vlsidlyarevich.unity.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class WorkerProfileSearchServiceImpl implements SearchService<WorkerProfile> {

    @Autowired
    private MongoOperations mongoOperations;

    @Override
    public List<WorkerProfile> find(Map<String, String> filters) {
        Query query = new Query();

        for (Map.Entry<String, String> filter : filters.entrySet()) {
            switch (filter.getKey()) {
                case "firstname": {
                    query.addCriteria(Criteria.where("name.firstName").is(filter.getValue()));
                    break;
                }
                case "secondname": {
                    query.addCriteria(Criteria.where("name.lastName").is(filter.getValue()));
                    break;
                }
                case "age": {
                    query.addCriteria(Criteria.where("age").is(Integer.valueOf(filter.getValue())));
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
}