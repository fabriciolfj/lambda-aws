package com.github.fabriciolfj.business;

import com.github.fabriciolfj.domain.model.Product;

import java.util.List;

public interface ProductRepository{

    void save(final List<Product> products);
    List<Product> findAll();
}