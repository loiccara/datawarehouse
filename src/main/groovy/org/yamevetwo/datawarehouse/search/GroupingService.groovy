package org.yamevetwo.datawarehouse.search

import org.springframework.stereotype.Service
import org.yamevetwo.datawarehouse.db.MetricLine
import org.yamevetwo.datawarehouse.search.query.DimensionEnum
import org.yamevetwo.datawarehouse.search.query.MetricEnum
import org.yamevetwo.datawarehouse.search.response.GroupedMetric

@Service
class GroupingService {

    private final MetricCalculationService metricCalculationService

    GroupingService(MetricCalculationService metricCalculationService){
        this.metricCalculationService = metricCalculationService
    }

    List<GroupedMetric> groupByDimension(List<MetricLine> metricLines, DimensionEnum groupingByEnum){
        metricLines
                .groupBy {
                    switch (groupingByEnum){
                        case DimensionEnum.DATASOURCE: return it.datasource
                        case DimensionEnum.CAMPAIGN: return it.campaign
                        case DimensionEnum.DATE: return it.date
                    }
                }
                .collect {
                    Integer totalClicks = it.value.sum{it.clicks}
                    Integer totalImpressions = it.value.sum{it.impressions}
                    new GroupedMetric(
                            totalClicks,
                            totalImpressions,
                            metricCalculationService.calculate(MetricEnum.CTR, totalClicks, totalImpressions),
                            it.key)
                }
    }
}
