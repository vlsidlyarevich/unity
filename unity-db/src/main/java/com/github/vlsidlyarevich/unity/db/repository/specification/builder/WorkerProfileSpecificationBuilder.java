package com.github.vlsidlyarevich.unity.db.repository.specification.builder;

import com.github.vlsidlyarevich.unity.db.model.WorkerProfile;
import com.github.vlsidlyarevich.unity.db.repository.specification.SearchCriteria;
import com.github.vlsidlyarevich.unity.db.repository.specification.WorkerProfileSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import java.util.ArrayList;
import java.util.List;

public class WorkerProfileSpecificationBuilder {

    private final List<SearchCriteria> params;

    public WorkerProfileSpecificationBuilder() {
        params = new ArrayList<>();
    }

    public WorkerProfileSpecificationBuilder with(String key, String operation, Object value) {
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public Specification<WorkerProfile> build() {
        if (params.size() == 0) {
            return null;
        }

        List<Specification<WorkerProfile>> specs = new ArrayList<>();
        for (SearchCriteria param : params) {
            specs.add(new WorkerProfileSpecification(param));
        }

        Specification<WorkerProfile> result = specs.get(0);
        for (int i = 1; i < specs.size(); i++) {
            result = Specifications.where(result).and(specs.get(i));
        }
        return result;
    }
}