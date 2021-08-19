package com.github.fabriciolfj.config;


import com.github.fabriciolfj.domain.exceptions.PropertiesException;

import java.io.IOException;
import java.io.InputStream;import java.util.Properties;

public class ConfigurationProperties {

    private static Properties properties;

    private ConfigurationProperties() { }

    public static Properties find() {
        if (properties != null) {
            return properties;
        }

        properties = new Properties();
        return loadProperties(properties);
    }

    private static Properties loadProperties(final Properties properties) {
        InputStream stream = null;
        try {
            stream = ConfigurationProperties.class.getClassLoader().getResourceAsStream("application.properties");
            properties.load(stream);
            return properties;
         } catch (Exception e) {
            throw new PropertiesException("File not found. Details: " + e.getMessage());
        } finally {
            closeStream(stream);
        }
    }

    private static void closeStream(InputStream stream) {
        try {
            if (stream != null) {
                stream.close();
            }
        } catch (IOException e) {
            throw new PropertiesException("Fail close stream. Details: " + e.getMessage());
        }
    }
}
