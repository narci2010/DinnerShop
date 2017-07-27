package com.dinner.model.domain;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by Tomek on 04-Feb-17.
 */
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ShoppingCart {

    private List<Product> products = new ArrayList<>();

    public Double getTotal() {
        return products.stream().mapToDouble(Product::getPrice).sum();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public List<Product> getProducts() {
        return Collections.unmodifiableList(products);
    }

    public void clear() {
        products.clear();
    }

    public boolean hasProduct(Product product) {
        return products.contains(product);
    }

}
