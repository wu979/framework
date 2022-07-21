package com.framework.cloud.elasticsearch.enums;

import lombok.AllArgsConstructor;

/**
 * @author wusiwei
 */
@AllArgsConstructor
public enum ElasticMessage {

    /** 错误消息 */
    SOURCE_NULL("Source cannot be null"),
    ELASTIC_DECLARE_NULL("ElasticDeclare annotation cannot be null"),
    CREATE_INDEX_ERROR("Create index error"),
    SAVE_SOURCE_ERROR("Document create error"),
    UPDATE_SOURCE_ERROR("Document update error"),
    UPDATE_MAPPING_ERROR("Update index mapping error"),
    DOCUMENT_NULL("Document not found"),
    SEARCH_TOTAL_ERROR("Search total error"),

    GET_DATA_ERROR("Failed to get data"),
    NOT_FOUND_ID("Please mark the primary key ID"),
    NOT_FOUND_DECLARE("Please check whether the annotation @ElasticDeclare is marked"),
    INDEX_ERROR("Index name or index type not found"),
    REQUEST_NULL("Query criteria is empty"),
    DATA_NULL("Data is null"),
    NOT_FOUND_ELASTIC_ID("Please check whether the annotation @ElasticId is marked")
    ;
    String msg;

    public String getMsg() {
        return msg;
    }
}
