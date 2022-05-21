package org.yamevetwo.datawarehouse.db

class SearchCriteria {
    final String key
    final String operation
    final Object value

    SearchCriteria(String key, String operation, String value){
        this.key = key
        this.operation = operation
        this.value = value
    }
}
