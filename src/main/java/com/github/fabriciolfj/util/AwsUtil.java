package com.github.fabriciolfj.util;

import com.github.fabriciolfj.config.ConfigurationProperties;

public class AwsUtil {

    public static String getDynamoUrl() {
        var props = ConfigurationProperties.find();
        return props.getProperty("aws-dynamodb-url");
    }
}
