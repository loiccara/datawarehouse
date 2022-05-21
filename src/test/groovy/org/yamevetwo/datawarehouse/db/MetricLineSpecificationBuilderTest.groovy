package org.yamevetwo.datawarehouse.db

import org.yamevetwo.datawarehouse.search.query.DimensionEnum
import org.yamevetwo.datawarehouse.search.query.DimensionFilter
import spock.lang.Specification

class MetricLineSpecificationBuilderTest extends Specification {

    private MetricLineSpecificationBuilder service

    def setup(){
        service = new MetricLineSpecificationBuilder()
    }

    def "should return an empty Specification for an empty list of filters"(){
        given:
        def filters = []

        expect:
        service.buildFilters(filters) == null
    }

    def "should return a specification containing all filters"(){
        given:
        def filters = [
                new DimensionFilter(dimension: DimensionEnum.DATASOURCE, value: "test1"),
                new DimensionFilter(dimension: DimensionEnum.CAMPAIGN, value: "test2")
        ]

        when:
        def result = service.buildFilters(filters)

        then:
        def specifications = result.properties*.value.findAll{it.class==MetricLineSpecification.class}
        specifications[0].getCriteria().key == DimensionEnum.DATASOURCE.value
        specifications[0].getCriteria().operation == ":"
        specifications[0].getCriteria().value == "test1"
        specifications[1].getCriteria().key == DimensionEnum.CAMPAIGN.value
        specifications[1].getCriteria().operation == ":"
        specifications[1].getCriteria().value == "test2"
    }
}
