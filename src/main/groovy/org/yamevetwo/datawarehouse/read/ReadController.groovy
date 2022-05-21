package org.yamevetwo.datawarehouse.read

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("read")
class ReadController {
    @Autowired
    private final ReadService readService

    ReadController(ReadService readService){
        this.readService = readService
    }

    @PostMapping
    def triggerRead(){
        readService.readAllFiles()
    }
}
