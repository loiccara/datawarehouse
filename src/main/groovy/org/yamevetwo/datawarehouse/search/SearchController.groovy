package org.yamevetwo.datawarehouse.search

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.yamevetwo.datawarehouse.search.query.SearchQuery

@RestController
@RequestMapping("/search")
class SearchController {

    private final SearchService searchService

    @Autowired
    SearchController(SearchService searchService){
        this.searchService = searchService
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    def search(@RequestBody SearchQuery searchQuery){
        searchService.search(searchQuery)
    }
}