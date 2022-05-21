package org.yamevetwo.datawarehouse.search.query

import groovy.transform.ToString
import org.yamevetwo.datawarehouse.search.query.DimensionEnum

@ToString
class DimensionFilter {
    DimensionEnum dimension
    String value
}
