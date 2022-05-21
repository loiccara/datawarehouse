package org.yamevetwo.datawarehouse.read

import spock.lang.Specification

class ReadControllerTest extends Specification {

    private ReadController controller
    private ReadService readServiceMock

    def setup(){
        readServiceMock = Mock(ReadService)
        controller = new ReadController(readServiceMock)
    }

    def "should call readService"(){
        when:
        controller.triggerRead()

        then:
        1 * readServiceMock.readAllFiles()
    }
}
