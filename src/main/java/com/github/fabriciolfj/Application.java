package com.github.fabriciolfj;

import com.github.fabriciolfj.business.usercase.ProductCase;
import com.github.fabriciolfj.domain.model.Product;

import java.util.List;

public class Application {

    public static void main(String[] args) {
        final ProductCase caseProduct = new ProductCase();

        caseProduct.execute();

        System.out.println("===============");

        System.out.println("=====List=======");
        final List<Product> products = caseProduct.listAll();
        System.out.println("Total : " + products.size());
    }
}
