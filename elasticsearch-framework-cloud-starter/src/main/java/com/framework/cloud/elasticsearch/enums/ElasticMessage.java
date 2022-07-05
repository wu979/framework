package com.framework.cloud.elasticsearch.enums;

import lombok.AllArgsConstructor;

/**
 * @author wusiwei
 */
@AllArgsConstructor
public enum ElasticMessage {

    /** 错误消息 */
    GET_DATA_ERROR("Failed to get data"),
    NOT_FOUND_ID("Please mark the primary key ID"),
    CLZ_NULL("Please pass in the return type"),
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
