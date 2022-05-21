package org.yamevetwo.datawarehouse.search

import org.springframework.stereotype.Service
import org.yamevetwo.datawarehouse.search.query.MetricEnum

import java.math.RoundingMode

@Service
class MetricCalculationService {

    private static final int SCALE = 6

    def calculate(MetricEnum metric, int clicks, int impressions){
        switch (metric){
            case MetricEnum.CTR:
                return BigDecimal.valueOf(clicks).divide(BigDecimal.valueOf(impressions), SCALE, RoundingMode.HALF_UP)
            default: throw new NonCalculatedMetricException(metric)
        }
    }
}
