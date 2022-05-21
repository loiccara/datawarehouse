package org.yamevetwo.datawarehouse.search.query

import groovy.transform.ToString

@ToString
class SearchQuery {
    List<MetricEnum> metrics
    List<DimensionFilter> filters
    DimensionEnum groupingBy
}
