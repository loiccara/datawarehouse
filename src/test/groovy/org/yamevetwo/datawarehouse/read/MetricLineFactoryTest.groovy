package org.yamevetwo.datawarehouse.read

import spock.lang.Specification

import java.time.LocalDate

class MetricLineFactoryTest extends Specification {

    private MetricLineFactory metricLineFactory

    def setup(){
        metricLineFactory = new MetricLineFactory()
    }

    def "should transform a line into a MetricLine" (){
        given:
        def datasource = "Google Ads"
        def campaign = "Adventmarkt Touristik"
        def date = LocalDate.of(2019, 11, 12)
        def clicks = 7
        def impressions = 22425
        def line = "$datasource,$campaign,11/12/19,$clicks,$impressions"

        when:
        def metricLine = metricLineFactory.from(line)

        then:
        metricLine.datasource == datasource
        metricLine.campaign == campaign
        metricLine.date == date
        metricLine.clicks == clicks
        metricLine.impressions == impressions
    }
}
