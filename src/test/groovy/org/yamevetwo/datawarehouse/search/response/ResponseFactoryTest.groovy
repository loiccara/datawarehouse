package org.yamevetwo.datawarehouse.search.response

import org.yamevetwo.datawarehouse.db.MetricLine
import org.yamevetwo.datawarehouse.search.MetricCalculationService
import spock.lang.Specification

import java.time.LocalDate

class ResponseFactoryTest extends Specification {

    private ResponseFactory factory
    private MetricCalculationService metricCalculationServiceSpy

    def setup(){
        metricCalculationServiceSpy = new MetricCalculationService()
        factory = new ResponseFactory(metricCalculationServiceSpy)
    }

    def "should build a response from a DB metric line"(){
        given:
        def metricLine = new MetricLine(
                id: 68732, datasource: "d", campaign: "c", date: LocalDate.now(),
                clicks: 53463, impressions: 653467854854
        )

        when:
        def responseMetric = factory.buildResponse(metricLine)

        then:
        responseMetric.datasource == metricLine.datasource
        responseMetric.campaign == metricLine.campaign
        responseMetric.date == metricLine.date
        responseMetric.clicks == metricLine.clicks
        responseMetric.impressions == metricLine.impressions
        responseMetric.ctr!=null
    }
}
