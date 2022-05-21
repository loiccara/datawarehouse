package org.yamevetwo.datawarehouse.search.response

import java.time.LocalDate

class ResponseMetric {

    Integer clicks
    Integer impressions
    BigDecimal ctr

    String datasource
    String campaign
    LocalDate date
}
