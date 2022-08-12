package com.github.fabriciolfj.business.usercase;

import com.github.fabriciolfj.business.FindProducts;
import com.github.fabriciolfj.domain.exceptions.BussinessException;
import com.github.fabriciolfj.business.ProductRepository;
import com.github.fabriciolfj.domain.model.Product;
import com.github.fabriciolfj.gateway.database.repository.ProductRepositoryImpl;
import com.github.fabriciolfj.gateway.providers.ProductProvider;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class ProductCase {

    final FindProducts find = new ProductProvider();
    final ProductRepository repo = new ProductRepositoryImpl();

    public void execute() {

        try {
            var products = productsFake();//find.process();
            log.info("Products: {}", products.size());
            repo.save(products);
        } catch (Exception e) {
            throw new BussinessException(e.getMessage());
        }
    }

    private List<Product> productsFake() {
        var index = 0;
        final List<Product> products = new ArrayList<>();

        while (index < 1000) {
            products.add(new Product(String.valueOf(index), "teste " + index, null));
            index++;
        }

        return products;
    }

    public List<Product> listAll() {
        try {
            return repo.findAll();
        } catch (Exception e) {
            throw new BussinessException(e.getMessage());
        }
    }

    private void checkSave(final String id) {
        repo.findById(id)
                .stream()
                .forEach(p -> log.info("Product save: {}", p.toString()));
    }
}
