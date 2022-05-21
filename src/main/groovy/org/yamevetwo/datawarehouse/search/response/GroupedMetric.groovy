package org.yamevetwo.datawarehouse.search.response

class GroupedMetric {

    final Integer totalClicks
    final Integer totalImpressions
    final BigDecimal ctr
    final Object dimension

    GroupedMetric(Integer totalClicks, Integer totalImpressions, BigDecimal ctr, Object dimension){
        this.totalClicks = totalClicks
        this.totalImpressions = totalImpressions
        this.ctr = ctr
        this.dimension = dimension
    }
}
