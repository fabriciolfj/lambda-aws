package com.github.fabriciolfj.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonMapperFactory {

    private static ObjectMapper instance;

    private JsonMapperFactory() {}

    public static ObjectMapper getInstance() {
        if (instance == null) {
            instance = new ObjectMapper();
        }

        return instance;
    }
}
