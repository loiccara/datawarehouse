package org.yamevetwo.datawarehouse.search

import org.yamevetwo.datawarehouse.db.MetricLine
import spock.lang.Specification
import spock.lang.Unroll

import java.time.LocalDate
import static org.yamevetwo.datawarehouse.search.query.DimensionEnum.DATASOURCE
import static org.yamevetwo.datawarehouse.search.query.DimensionEnum.CAMPAIGN
import static org.yamevetwo.datawarehouse.search.query.DimensionEnum.DATE

class GroupingServiceTest extends Specification {

    private GroupingService service
    private MetricCalculationService metricCalculationService

    def setup(){
        metricCalculationService = new MetricCalculationService()
        service = new GroupingService(metricCalculationService)
    }

    @Unroll
    def "should group metrics correctly"(){
        when:
        def result = service.groupByDimension(getSomeMetrics(), dimension)

        then:
        result[0].dimension == dimensionValue[0]
        result[0].totalClicks == totalClicks[0]
        result[0].totalImpressions == totalImpressions[0]
        result[1].dimension == dimensionValue[1]
        result[1].totalClicks == totalClicks[1]
        result[1].totalImpressions == totalImpressions[1]
        result[2].dimension == dimensionValue[2]
        result[2].totalClicks == totalClicks[2]
        result[2].totalImpressions == totalImpressions[2]

        where:
        dimension   | totalClicks   | totalImpressions      | dimensionValue
        DATASOURCE  | [23, 27, 15]  | [3002, 7002, 5001]    | ["d1", "d2", "d3"]
        CAMPAIGN    | [24, 26, 15]  | [4002, 6002, 5001]    | ["c1", "c2", "c3"]
        DATE        | [26, 25, 14]  | [6002, 5002, 4001]    | [LocalDate.of(2020, 07,29), LocalDate.of(2020, 07,2), LocalDate.of(2020, 07,4)]
    }

    private List<MetricLine> getSomeMetrics(){
        [
                new MetricLine(datasource: "d1", campaign: "c1", date: LocalDate.of(2020, 07,29), clicks:11 ,impressions:1001),
                new MetricLine(datasource: "d1", campaign: "c2", date: LocalDate.of(2020, 07,2), clicks:12 ,impressions:2001),
                new MetricLine(datasource: "d2", campaign: "c1", date: LocalDate.of(2020, 07,2), clicks:13 ,impressions:3001),
                new MetricLine(datasource: "d2", campaign: "c2", date: LocalDate.of(2020, 07,4), clicks:14 ,impressions:4001),
                new MetricLine(datasource: "d3", campaign: "c3", date: LocalDate.of(2020, 07,29), clicks:15 ,impressions:5001)
        ]
    }
}
