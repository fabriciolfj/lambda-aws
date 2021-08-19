package com.github.fabriciolfj.providers.database.dynamodb.definition;

public enum ProductDynamoDbDefinition {
    TABLE_NAME("Product"),
    PRIMARY_KEY("ID"),
    NAME("name");

    private final String value;

    ProductDynamoDbDefinition(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
