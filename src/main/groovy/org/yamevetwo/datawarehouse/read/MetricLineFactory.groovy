package org.yamevetwo.datawarehouse.read

import org.springframework.stereotype.Service
import org.yamevetwo.datawarehouse.db.MetricLine

import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Service
class MetricLineFactory {

    private final int DATASOURCE_FIELD=0
    private final int CAMPAIGN_FIELD=1
    private final int DATE_FIELD=2
    private final int CLICKS_FIELD=3
    private final int IMPRESSIONS_FIELD=4
    private final String SEPARATOR = ','

    MetricLine from(String line){
        def fields = line.split(SEPARATOR)

        new MetricLine(
                datasource: fields[DATASOURCE_FIELD],
                campaign: fields[CAMPAIGN_FIELD],
                date: getLocalDate(fields[DATE_FIELD]),
                clicks: fields[CLICKS_FIELD] as Integer,
                impressions: fields[IMPRESSIONS_FIELD] as Integer
        )
    }

    private LocalDate getLocalDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy")
        LocalDate.parse(date, formatter)
    }
}
