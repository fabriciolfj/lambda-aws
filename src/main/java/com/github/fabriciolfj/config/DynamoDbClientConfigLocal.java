package com.github.fabriciolfj.config;

import com.github.fabriciolfj.util.AwsUtil;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.net.URI;

public class DynamoDbClientConfigLocal {

    private static DynamoDbClient instance;

    public synchronized static DynamoDbClient getInstance() {
        if (instance == null) {
            instance = dynamoDbClientLocal();
        }

        return instance;
    }

    private static DynamoDbClient dynamoDbClientLocal() {
        final String url = AwsUtil.getDynamoUrl();
        final AwsCredentials awsCredentials = AwsBasicCredentials.create("accessKey", "secretKey");
        final AwsCredentialsProvider awsCredentialsProvider = StaticCredentialsProvider
                .create(awsCredentials);
        return DynamoDbClient.builder()
                .credentialsProvider(awsCredentialsProvider)
                .region(Region.of("us-east-1"))
                .endpointOverride(URI.create(url))
                .build();
    }
}