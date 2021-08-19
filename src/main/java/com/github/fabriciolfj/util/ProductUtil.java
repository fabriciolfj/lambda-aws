package com.github.fabriciolfj.util;

import com.github.fabriciolfj.config.ConfigurationProperties;

public class ProductUtil {

    public static String getUrl() {
        var properties = ConfigurationProperties.find();
        return properties.getProperty("product-url");
    }
}
