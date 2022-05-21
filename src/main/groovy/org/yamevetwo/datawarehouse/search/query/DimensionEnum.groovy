package org.yamevetwo.datawarehouse.search.query

import groovy.transform.ToString

@ToString
enum DimensionEnum {
    CAMPAIGN("campaign", DimensionTypeEnum.REGULAR),
    DATASOURCE("datasource", DimensionTypeEnum.REGULAR),
    DATE("date", DimensionTypeEnum.TIME)

    final String value
    final DimensionTypeEnum type

    private DimensionEnum(String value, DimensionTypeEnum type){
        this.value = value
        this.type = type
    }

    enum DimensionTypeEnum {
        REGULAR,
        TIME
    }
}


