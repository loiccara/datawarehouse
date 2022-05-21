package org.yamevetwo.datawarehouse.db

import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import org.yamevetwo.datawarehouse.search.query.DimensionFilter

@Service
class MetricLineSpecificationBuilder {

    Specification buildFilters(List<DimensionFilter> filters){
        List<MetricLineSpecification> metricsSpecs = filters?.collect{
            return new MetricLineSpecification(new SearchCriteria(it.dimension.value, ":", it.value))
        }
        Specification specification
        metricsSpecs.eachWithIndex{ MetricLineSpecification entry, int index ->
            specification = (index==0) ? Specification.where(metricsSpecs[0]) : specification.and(metricsSpecs[index])
        }

        return specification
    }
}
