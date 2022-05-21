package org.yamevetwo.datawarehouse.search

import org.yamevetwo.datawarehouse.search.query.SearchQuery
import spock.lang.Specification

class SearchControllerTest extends Specification {

    private SearchController controller
    private SearchService searchServiceMock

    def setup(){
        searchServiceMock = Mock(SearchService)
        controller = new SearchController(searchServiceMock)
    }

    def "should call search service"(){
        given:
        def searchQuery = new SearchQuery(metrics: [])

        when:
        controller.search(searchQuery)

        then:
        1 * searchServiceMock.search(searchQuery)
    }
}
