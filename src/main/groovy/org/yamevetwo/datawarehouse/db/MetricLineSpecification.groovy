package org.yamevetwo.datawarehouse.db

import org.springframework.data.jpa.domain.Specification

import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

/**
 * Not gonna lie. I stole this code, as I find it extremely powerful to build dynamic queries:
 * https://www.baeldung.com/rest-api-search-language-spring-data-specifications
 *
 * I am keeping this code as is for the time being, for possible filters improvements later.
 */
class MetricLineSpecification implements Specification<MetricLine>{

    private final SearchCriteria criteria

    MetricLineSpecification(SearchCriteria criteria){
        this.criteria = criteria
    }

    @Override
    Predicate toPredicate(Root<MetricLine> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        if (criteria.getOperation().equalsIgnoreCase(">")) {
            return builder.greaterThanOrEqualTo(
                    root.<String> get(criteria.getKey()), criteria.getValue().toString());
        }
        else if (criteria.getOperation().equalsIgnoreCase("<")) {
            return builder.lessThanOrEqualTo(
                    root.<String> get(criteria.getKey()), criteria.getValue().toString());
        }
        else if (criteria.getOperation().equalsIgnoreCase(":")) {
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return builder.like(
                        root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
            } else {
                return builder.equal(root.get(criteria.getKey()), criteria.getValue())
            }
        }
        return null;
    }

    SearchCriteria getCriteria(){
        criteria
    }
}
