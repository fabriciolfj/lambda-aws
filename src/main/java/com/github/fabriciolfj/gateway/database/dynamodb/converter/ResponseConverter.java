package com.github.fabriciolfj.gateway.database.dynamodb.converter;

import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.Map;

public interface ResponseConverter<T> {

    T fromEntity(final Map<String, AttributeValue> entry);
}
