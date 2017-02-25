package com.dinner.model.business;

import lombok.Getter;
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

    public synchronized void clear() {
        products.clear();
    }

    public synchronized boolean hasProduct(Product product) {
        return products.contains(product);
    }

}
