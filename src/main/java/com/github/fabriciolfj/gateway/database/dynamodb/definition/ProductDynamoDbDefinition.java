package com.github.fabriciolfj.gateway.database.dynamodb.definition;

public enum ProductDynamoDbDefinition {
    TABLE_NAME("Product"),
    PRIMARY_KEY("ID"),
    NAME("nome"),
    DETAILS("details");

    private final String value;

    ProductDynamoDbDefinition(final String value) {
        this.value = value;
    }

    public String  getValue() {
        return value;
    }
}
