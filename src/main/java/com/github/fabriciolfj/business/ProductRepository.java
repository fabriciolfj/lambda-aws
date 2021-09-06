package com.github.fabriciolfj.business;

import com.github.fabriciolfj.domain.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository{

    void save(final List<Product> products);
    List<Product> findAll();
    Optional<Product> findById(final String id);
}
