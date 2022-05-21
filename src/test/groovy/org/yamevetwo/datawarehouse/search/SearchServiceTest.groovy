package org.yamevetwo.datawarehouse.search

import org.yamevetwo.datawarehouse.db.MetricLineRepository
import org.yamevetwo.datawarehouse.db.MetricLineSpecificationBuilder
import org.yamevetwo.datawarehouse.search.query.DimensionEnum
import org.yamevetwo.datawarehouse.search.query.DimensionFilter
import org.yamevetwo.datawarehouse.search.query.SearchQuery
import org.yamevetwo.datawarehouse.search.response.ResponseFactory
import spock.lang.Specification


class SearchServiceTest extends Specification {

    private SearchService service
    private MetricLineRepository metricLineRepositoryMock
    private MetricLineSpecificationBuilder specificationBuilderSpy
    private GroupingService groupingServiceSpy
    private ResponseFactory responseFactoryMock

    def setup(){
        metricLineRepositoryMock = Mock(MetricLineRepository)
        specificationBuilderSpy = Spy(MetricLineSpecificationBuilder)
        groupingServiceSpy = Spy(GroupingService)
        responseFactoryMock = Mock(ResponseFactory)
        service = new SearchService(metricLineRepositoryMock, specificationBuilderSpy, groupingServiceSpy, responseFactoryMock)
    }

    def "should find all when there is no filter"(){
        given:
        def searchQuery = new SearchQuery(metrics: [], filters: filters)

        when:
        service.search(searchQuery)

        then:
        0 * specificationBuilderSpy.buildFilters(filters)
        1 * metricLineRepositoryMock.findAll()

        where:
        filters << [null, []]
    }

    def "should filter when there is a list of filters"(){
        given:
        def filters = [new DimensionFilter(dimension: DimensionEnum.DATASOURCE, value: "some datasource")]
        def searchQuery = new SearchQuery(metrics: [], filters: filters)

        when:
        service.search(searchQuery)

        then:
        1 * specificationBuilderSpy.buildFilters(filters)
        1 * metricLineRepositoryMock.findAll(_)
    }

    def "should find all and group by when there is no filter"(){
        given:
        def groupingDimension = DimensionEnum.DATASOURCE
        def searchQuery = new SearchQuery(metrics: [], filters: filters, groupingBy: groupingDimension)

        when:
        service.search(searchQuery)

        then:
        0 * specificationBuilderSpy.buildFilters(filters)
        1 * metricLineRepositoryMock.findAll() >> []
        1 * groupingServiceSpy.groupByDimension([], groupingDimension)

        where:
        filters << [null, []]
    }

    def "should filter and group by when there is a list of filters"(){
        given:
        def groupingDimension = DimensionEnum.DATASOURCE
        def filters = [new DimensionFilter(dimension: DimensionEnum.DATASOURCE, value: "some datasource")]
        def searchQuery = new SearchQuery(metrics: [], filters: filters, groupingBy: groupingDimension)

        when:
        service.search(searchQuery)

        then:
        1 * specificationBuilderSpy.buildFilters(filters)
        1 * metricLineRepositoryMock.findAll(_) >> []
        1 * groupingServiceSpy.groupByDimension([], groupingDimension)
    }
}
