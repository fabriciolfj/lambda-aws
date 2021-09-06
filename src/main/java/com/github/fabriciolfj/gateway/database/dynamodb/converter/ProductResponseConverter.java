package com.github.fabriciolfj.gateway.database.dynamodb.converter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fabriciolfj.domain.model.Details;
import com.github.fabriciolfj.domain.model.Product;
import com.github.fabriciolfj.util.JsonMapperFactory;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.github.fabriciolfj.gateway.database.dynamodb.definition.ProductDynamoDbDefinition.DETAILS;
import static com.github.fabriciolfj.gateway.database.dynamodb.definition.ProductDynamoDbDefinition.NAME;
import static com.github.fabriciolfj.gateway.database.dynamodb.definition.ProductDynamoDbDefinition.PRIMARY_KEY;

public class ProductResponseConverter implements ResponseConverter<Product> {

    private ObjectMapper mapper = JsonMapperFactory.getInstance();

    @Override
    public Product fromEntity(final Map<String, AttributeValue> entry) {
        final Product product = new Product();

        Optional.ofNullable(entry.get(PRIMARY_KEY.getValue()))
                .ifPresent(v -> product.setId(v.s()));

        Optional.ofNullable(entry.get(DETAILS.getValue()))
                .ifPresent(v -> {
                    List<Details> detail = new ArrayList<>();
                    try {
                        TypeReference<List<Details>> mapType = new TypeReference<>() {};
                        detail = mapper.readValue(v.s(), mapType);
                    } catch (Exception e) {

                    }

                    detail.stream().forEach(d -> System.out.println(d));
                    product.setDetails(detail);
                });

        Optional.ofNullable(entry.get(NAME.getValue()))
                .ifPresent(v -> product.setName(v.s()));

        return product;
    }
}
