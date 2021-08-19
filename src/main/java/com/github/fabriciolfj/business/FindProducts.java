package com.github.fabriciolfj.business;

import com.github.fabriciolfj.domain.model.Product;

import java.util.List;

public interface FindProducts {


    List<Product> process() throws Exception;
}
