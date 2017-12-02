package com.github.vlsidlyarevich.unity.db.repository.specification;

import com.github.vlsidlyarevich.unity.db.model.WorkerProfile;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class WorkerProfileSpecification implements Specification<WorkerProfile> {

    private SearchCriteria criteria;

    public WorkerProfileSpecification(SearchCriteria searchCriteria) {
        super();
        this.criteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate
            (Root<WorkerProfile> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        if (criteria.getOperation().equalsIgnoreCase(">")) {
            return builder.greaterThanOrEqualTo(
                    root.get(criteria.getKey()), criteria.getValue().toString());
        } else if (criteria.getOperation().equalsIgnoreCase("<")) {
            return builder.lessThanOrEqualTo(
                    root.get(criteria.getKey()), criteria.getValue().toString());
        } else if (criteria.getOperation().equalsIgnoreCase(":")) {
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return builder.like(
                        root.get(criteria.getKey()), "%" + criteria.getValue() + "%");
            } else {
                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
            }
        } else if (criteria.getOperation().equalsIgnoreCase(":name")) {
            if (root.get("name").get(criteria.getKey()).getJavaType() == String.class) {
                return builder.like(
                        root.get("name").get(criteria.getKey()), "%" + criteria.getValue() + "%");
            } else {
                return builder.equal(root.get("name").get(criteria.getKey()), criteria.getValue());
            }
        }
        return null;
    }
}