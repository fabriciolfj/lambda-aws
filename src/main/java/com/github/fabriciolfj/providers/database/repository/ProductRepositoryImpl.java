package com.github.fabriciolfj.providers.database.repository;

import com.github.fabriciolfj.config.DynamoDbClientConfigLocal;
import com.github.fabriciolfj.domain.model.Product;
import com.github.fabriciolfj.gateway.ProductRepository;
import com.github.fabriciolfj.providers.database.dynamodb.builder.ProductPutItemBuilder;
import com.github.fabriciolfj.providers.database.dynamodb.converter.ProductResponseConverter;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanRequest;

import java.util.List;
import java.util.stream.Collectors;

import static com.github.fabriciolfj.providers.database.dynamodb.definition.ProductDynamoDbDefinition.TABLE_NAME;

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
        return dynamoDbClient.scan(ScanRequest.builder()
                .tableName(TABLE_NAME.getValue()).build())
                .items()
                .stream()
                .map(productResponseConverter::fromEntity)
                .collect(Collectors.toList());
    }

    private void execute(final Product product) {
        final PutItemRequest request = ProductPutItemBuilder.builder()
                .withEntity(product)
                .build();
        dynamoDbClient.putItem(request);
    }
}
