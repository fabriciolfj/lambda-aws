package com.github.fabriciolfj.gateway.providers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fabriciolfj.business.FindProducts;
import com.github.fabriciolfj.domain.model.Product;
import com.github.fabriciolfj.gateway.providers.http.ProductHttp;
import com.github.fabriciolfj.util.JsonMapperFactory;
import com.github.fabriciolfj.util.ProductUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class ProductProvider implements FindProducts {

    private final ProductHttp http;
    private final ObjectMapper objectMapper;

    public ProductProvider() {
        this.http = new ProductHttp();
        objectMapper = JsonMapperFactory.getInstance();
    }

    @Override
    public List<Product> process() throws Exception {
        try{
            var result = findAll();
            log.info("Return json products: {}", result);

            return objectMapper.readValue(result, new TypeReference<>() {});
        } catch (Exception e) {
            log.error("Fail mapper to json. Details: {}", e.getMessage());
            throw e;
        }
    }

    private String findAll() throws Exception {
        try {
            return http.request(ProductUtil.getUrl());
        } catch (Exception e) {
            log.error("Fail request. Details: {}", e.getMessage());
            throw e;
        }
    }

}
