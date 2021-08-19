package com.github.fabriciolfj.business.usercase;

import com.github.fabriciolfj.business.FindProducts;
import com.github.fabriciolfj.domain.exceptions.BussinessException;
import com.github.fabriciolfj.business.ProductRepository;
import com.github.fabriciolfj.gateway.database.repository.ProductRepositoryImpl;
import com.github.fabriciolfj.gateway.providers.ProductProvider;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProductCase {

    final FindProducts find = new ProductProvider();
    final ProductRepository repo = new ProductRepositoryImpl();

    public void execute() {

        try {
            var products = find.process();
            log.info("Products: {}", products.size());
            repo.save(products);
            checkSave();
        } catch (Exception e) {
            throw new BussinessException(e.getMessage());
        }
    }

    private void checkSave() {
        repo.findAll()
                .stream()
                .forEach(p -> log.info("Product save: {}", p.toString()));
    }
}
