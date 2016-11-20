package com.github.vlsidlyarevich.unity.service.impl;

import com.github.vlsidlyarevich.unity.model.WorkerProfile;
import com.github.vlsidlyarevich.unity.service.SearchService;
import org.neo4j.ogm.cypher.BooleanOperator;
import org.neo4j.ogm.cypher.Filter;
import org.neo4j.ogm.cypher.Filters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class WorkerProfileSearchServiceImpl implements SearchService<WorkerProfile> {

    @Autowired
    private Neo4jOperations neo4jOperations;

    @Override
    public List<WorkerProfile> find(Map<String, String> filters) {
        Filters searchFilters = new Filters();

        for (Map.Entry<String, String> filter : filters.entrySet()) {
            switch (filter.getKey()) {
                case "age": {
                    Filter searchFilter = new Filter();
                    searchFilter.setBooleanOperator(BooleanOperator.AND);
                    searchFilter.setPropertyName("age");
                    searchFilter.setPropertyValue(filter.getValue());
                    searchFilters.add(searchFilter);
                    break;
                }
                case "gender": {
                    Filter searchFilter = new Filter();
                    searchFilter.setBooleanOperator(BooleanOperator.AND);
                    searchFilter.setPropertyName("gender");
                    searchFilter.setPropertyValue(filter.getValue());
                    searchFilters.add(searchFilter);
                    break;
                }
                case "speciality": {
                    Filter searchFilter = new Filter();
                    searchFilter.setBooleanOperator(BooleanOperator.AND);
                    searchFilter.setPropertyName("speciality");
                    searchFilter.setPropertyValue(filter.getValue());
                    searchFilters.add(searchFilter);
                    break;
                }
                case "skype": {
                    Filter searchFilter = new Filter();
                    searchFilter.setBooleanOperator(BooleanOperator.AND);
                    searchFilter.setPropertyName("skype");
                    searchFilter.setPropertyValue(filter.getValue());
                    searchFilters.add(searchFilter);
                    break;
                }
            }
        }

        return new ArrayList<>(neo4jOperations.loadAllByProperties(WorkerProfile.class, searchFilters));
    }
}
