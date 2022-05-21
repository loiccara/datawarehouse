package org.yamevetwo.datawarehouse.db

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface MetricLineRepository extends JpaRepository<MetricLine, Long>, JpaSpecificationExecutor<MetricLine> {

}