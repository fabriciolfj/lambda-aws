package com.github.fabriciolfj;

import com.github.fabriciolfj.business.usercase.ProductCase;

public class Application {

    public static void main(String[] args) {
        final ProductCase caseProduct = new ProductCase();
        caseProduct.execute();
    }
}
