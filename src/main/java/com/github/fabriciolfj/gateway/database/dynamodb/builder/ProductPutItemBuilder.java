package com.github.fabriciolfj.gateway.database.dynamodb.builder;


import com.github.fabriciolfj.domain.model.Product;
import com.github.fabriciolfj.gateway.database.dynamodb.definition.ProductDynamoDbDefinition;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.utils.StringUtils;
import software.amazon.awssdk.utils.builder.SdkBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.github.fabriciolfj.gateway.database.dynamodb.definition.ProductDynamoDbDefinition.*;

public class ProductPutItemBuilder {

    private PutItemRequest.Builder  putItemRequest;

    public static ProductPutItemBuilder builder() {
        return new ProductPutItemBuilder();
    }

    public ProductPutItemBuilder withEntity(final Product product) {
        final Map<String, AttributeValue> attributes = new HashMap<>();

        Optional.ofNullable(product.getId())
                .ifPresent(value -> attributes.put(PRIMARY_KEY.getValue(), withS(value)));

        Optional.ofNullable(product.getName())
                .ifPresent(value -> attributes.put(NAME.getValue(), withS(value)));

        putItemRequest = PutItemRequest.builder().item(attributes);
        return this;
    }

    public PutItemRequest build() {
        return Optional.ofNullable(TABLE_NAME)
                .map(ProductDynamoDbDefinition::getValue)
                .filter(StringUtils::isNotBlank)
                .map(putItemRequest::tableName)
                .map(SdkBuilder::build)
                .orElseThrow(IllegalArgumentException::new);
    }

    private static <T> AttributeValue withS(final T value) {
        return Optional.ofNullable(value)
                .map(v -> AttributeValue.builder().s(String.valueOf(v)).build())
                .orElse(null);
    }
}
