package org.yamevetwo.datawarehouse.read

import groovy.io.FileType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.yamevetwo.datawarehouse.db.MetricLine
import org.yamevetwo.datawarehouse.db.MetricLineRepository

@Component
class ReadService {

    @Value('${input.todo.path}')
    private String inputFile;

    @Autowired
    private final MetricLineFactory metricLineFactory

    @Autowired
    private final MetricLineRepository metricLineRepository

    def readAllFiles(){
        def list = []
        def dir = new File(inputFile)

        dir.eachFileRecurse (FileType.FILES) { file ->
            list << file
            readOneFile(file)
        }
    }

    private readOneFile(File file){
        boolean firstline = true
        file.eachLine {
            if (firstline) {
                firstline = false
                return
            }
            metricLineRepository.save(transformLine(it))
        }
    }

    private MetricLine transformLine(String line){
        metricLineFactory.from(line)
    }
}
