package org.yamevetwo.datawarehouse.db

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import java.time.LocalDate

@Entity
class MetricLine {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    long id
    String datasource
    String campaign
    LocalDate date
    int clicks
    int impressions
}