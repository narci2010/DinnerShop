package com.dinner.model.domain;

import com.dinner.model.domain.product.Product;
import com.dinner.model.value.objects.Money;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.*;


/**
 * Created by Tomek on 04-Feb-17.
 */
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ShoppingCart {

    private Map<Product, Integer> products = new HashMap<>();

    public Money getTotal() {
        Money money = new Money(0.00, "PLN");
        products.entrySet().forEach(entry -> money.add(entry.getKey().getPrice().multiplyBy(entry.getValue())));
        return money;
    }

    public void addProduct(Product product, Integer quantity) {
        products.put(product, quantity);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public Map<Product, Integer> getProducts() {
        return Collections.unmodifiableMap(products);
    }

    public void clear() {
        products.clear();
    }

//    public boolean hasProduct(Product product) {
//        return products.contains(product);
//    }

}
