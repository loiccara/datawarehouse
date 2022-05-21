package org.yamevetwo.datawarehouse.search

import org.yamevetwo.datawarehouse.search.query.MetricEnum

class NonCalculatedMetricException extends RuntimeException{

    NonCalculatedMetricException(MetricEnum metricEnum){
        super("$metricEnum is not a calculated Metric")
    }
}