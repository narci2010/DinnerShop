package com.dinner.model.business;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by Tomek on 04-Feb-17.
 */

public class ShoppingCart {

    private List<Product> products = new ArrayList<>();

    public synchronized Double getTotal() {
        return products.stream().mapToDouble(Product::getPrice).sum();
    }

    public synchronized void addProduct(Product product) {
        products.add(product);
    }

    public synchronized void removeProduct(Product product) {
        products.remove(product);
    }

    public synchronized List<Product> getProducts() {
        return Collections.unmodifiableList(products);
    }

    public synchronized boolean hasProduct(Product product) {
        return products.contains(product);
    }

}
