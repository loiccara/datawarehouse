package org.yamevetwo.datawarehouse.search

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.yamevetwo.datawarehouse.db.MetricLineRepository
import org.yamevetwo.datawarehouse.db.MetricLineSpecificationBuilder
import org.yamevetwo.datawarehouse.db.MetricLine
import org.yamevetwo.datawarehouse.search.query.SearchQuery
import org.yamevetwo.datawarehouse.search.response.ResponseFactory

@Service
class SearchService {

    private final MetricLineRepository repository
    private final MetricLineSpecificationBuilder specificationBuilder
    private final GroupingService groupingService
    private final ResponseFactory responseFactory

    @Autowired
    SearchService(
            MetricLineRepository metricLineRepository,
            MetricLineSpecificationBuilder specificationBuilder,
            GroupingService groupingService,
            ResponseFactory responseFactory){
        this.repository = metricLineRepository
        this.specificationBuilder = specificationBuilder
        this.groupingService = groupingService
        this.responseFactory = responseFactory
    }

    List search(SearchQuery searchQuery){
        List<MetricLine> filteredResults = searchQuery.filters?.size()>0 ?
                repository.findAll(specificationBuilder.buildFilters(searchQuery.filters)) :
                repository.findAll()

        return searchQuery.groupingBy ?
                groupingService.groupByDimension(filteredResults, searchQuery.groupingBy) :
                filteredResults.collect{responseFactory.buildResponse(it)}
    }
}