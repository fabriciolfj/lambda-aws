package com.github.fabriciolfj.gateway.database.dynamodb.converter;

import com.github.fabriciolfj.domain.model.Product;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.Map;
import java.util.Optional;

import static com.github.fabriciolfj.gateway.database.dynamodb.definition.ProductDynamoDbDefinition.NAME;
import static com.github.fabriciolfj.gateway.database.dynamodb.definition.ProductDynamoDbDefinition.PRIMARY_KEY;

public class ProductResponseConverter implements ResponseConverter<Product> {

    @Override
    public Product fromEntity(final Map<String, AttributeValue> entry) {
        final Product product = new Product();

        Optional.ofNullable(entry.get(PRIMARY_KEY.getValue()))
                .ifPresent(v -> product.setId(v.s()));

        Optional.ofNullable(entry.get(NAME.getValue()))
                .ifPresent(v -> product.setName(v.s()));

        return product;
    }
}
