package com.github.vlsidlyarevich.unity.db.service.impl;

import com.github.vlsidlyarevich.unity.db.model.WorkerProfile;
import com.github.vlsidlyarevich.unity.db.repository.WorkerProfileRepository;
import com.github.vlsidlyarevich.unity.db.repository.specification.builder.WorkerProfileSpecificationBuilder;
import com.github.vlsidlyarevich.unity.db.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class WorkerProfileSearchServiceImpl implements SearchService<WorkerProfile> {

    @Autowired
    private WorkerProfileRepository repository;

    @Override
    public List<WorkerProfile> find(Map<String, String> filters) {
        WorkerProfileSpecificationBuilder builder = new WorkerProfileSpecificationBuilder();

        for (Map.Entry<String, String> filter : filters.entrySet()) {
            switch (filter.getKey()) {
                case "firstname": {
                    builder.with("firstName", ":name", filter.getValue());
                    break;
                }
                case "secondname": {
                    builder.with("lastName", ":name", filter.getValue());
                    break;
                }
                case "age": {
                    builder.with("age", ":", filter.getValue());
                    break;
                }
                case "gender": {
                    builder.with("gender", ":", filter.getValue());
                    break;
                }
                case "speciality": {
                    builder.with("speciality", ":", filter.getValue());
                    break;
                }
                case "skype": {
                    builder.with("skype", ":", filter.getValue());
                    break;
                }
            }
        }
        return repository.findAll(builder.build());
    }
}