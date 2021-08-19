package com.github.fabriciolfj;

import com.github.fabriciolfj.business.usercase.ProductCase;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Application {


    public static void main(String[] args) {
        final ProductCase caseProduct = new ProductCase();
        caseProduct.execute();
    }
}
