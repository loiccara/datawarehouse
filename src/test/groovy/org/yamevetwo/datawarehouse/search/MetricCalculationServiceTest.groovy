package org.yamevetwo.datawarehouse.search

import org.yamevetwo.datawarehouse.search.query.MetricEnum
import spock.lang.Specification

class MetricCalculationServiceTest extends Specification {

    private MetricCalculationService service

    def setup(){
        service = new MetricCalculationService()
    }

    def "should calculate a click through rate"(){
        given:
        def metric = MetricEnum.CTR

        expect:
        service.calculate(metric, 1000, 87345678) == 0.000011
    }

    def "should throw an exception for non calculated metrics"(){
        when:
        service.calculate(metric, 1000, 87345678)

        then:
        def exception = thrown(NonCalculatedMetricException)

        where:
        metric << [MetricEnum.CLICKS, MetricEnum.IMPRESSIONS]
    }
}
