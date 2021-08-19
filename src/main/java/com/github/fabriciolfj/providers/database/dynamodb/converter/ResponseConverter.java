package com.github.fabriciolfj.providers.database.dynamodb.converter;

import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.Map;

public interface ResponseConverter<T> {

    T fromEntity(final Map<String, AttributeValue> entry);
}
