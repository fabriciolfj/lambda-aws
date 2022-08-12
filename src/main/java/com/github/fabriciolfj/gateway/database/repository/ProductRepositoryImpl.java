package com.github.fabriciolfj.gateway.database.repository;

import com.github.fabriciolfj.config.DynamoDbClientConfigLocal;
import com.github.fabriciolfj.domain.model.Product;
import com.github.fabriciolfj.business.ProductRepository;
import com.github.fabriciolfj.gateway.database.dynamodb.builder.ProductPutItemBuilder;
import com.github.fabriciolfj.gateway.database.dynamodb.converter.ProductResponseConverter;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.services.dynamodb.model.QueryRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanRequest;

import java.util.*;
import java.util.stream.Collectors;

import static com.github.fabriciolfj.gateway.database.dynamodb.definition.ProductDynamoDbDefinition.TABLE_NAME;

@Slf4j
public class ProductRepositoryImpl implements ProductRepository {

    private DynamoDbClient dynamoDbClient;
    private ProductResponseConverter productResponseConverter;

    public ProductRepositoryImpl() {
        dynamoDbClient = DynamoDbClientConfigLocal.getInstance();
        productResponseConverter = new ProductResponseConverter();
    }

    @Override
    public void save(final List<Product> products) {
        products.stream().forEach(this::execute);
    }

    @Override
    public List<Product> findAll() {
        final Map<String, AttributeValue> filter = new HashMap<>();
        final List<Map<String, AttributeValue>> values = new ArrayList<>();
        Map<String, AttributeValue> keyFinal = null;
        var index = 0;
        var next = false;

        filter.put(":value", AttributeValue.builder().s("teste 1").build());
        do {
            var result = dynamoDbClient.scan(ScanRequest.builder()
                    .filterExpression("nome = :value")
                    .expressionAttributeValues(filter)
                    .exclusiveStartKey(keyFinal)
                    .limit(10)
                    .tableName(TABLE_NAME.getValue()).build());

            log.info("Quantidade items {}", result.count());

            values.addAll(result.items());

            keyFinal = result.lastEvaluatedKey();
            next = result.hasLastEvaluatedKey();
            index++;
        } while(next);

        log.info("Total iteracao {}", index);

        return values
                .stream()
                .map(productResponseConverter::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Product> findById(final String id) {
        final QueryRequest query = ProductPutItemBuilder.builder().withId(id)
                .buildQuery();
        return dynamoDbClient.query(query)
                .items()
                .stream()
                .map(productResponseConverter::fromEntity)
                .findFirst();
    }

    private void execute(final Product product) {
        product.enrichDetails();
        final PutItemRequest request = ProductPutItemBuilder.builder()
                .withEntity(product)
                .build();
        dynamoDbClient.putItem(request);
    }
}
