package org.yamevetwo.datawarehouse.search.response

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.yamevetwo.datawarehouse.db.MetricLine
import org.yamevetwo.datawarehouse.search.MetricCalculationService
import org.yamevetwo.datawarehouse.search.query.MetricEnum

@Service
class ResponseFactory {

    private final MetricCalculationService metricCalculationService

    @Autowired
    ResponseFactory(MetricCalculationService metricCalculationService){
        this.metricCalculationService = metricCalculationService
    }

    ResponseMetric buildResponse(MetricLine metricLine){
        new ResponseMetric(
                clicks: metricLine.clicks,
                impressions: metricLine.impressions,
                ctr: metricCalculationService.calculate(MetricEnum.CTR, metricLine.clicks, metricLine.impressions),
                date: metricLine.date,
                datasource: metricLine.datasource,
                campaign: metricLine.campaign
        )
    }
}
