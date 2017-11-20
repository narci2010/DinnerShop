package com.dinner.model.domain;

import com.dinner.model.domain.product.Product;
import com.dinner.model.value.objects.Money;

import java.util.*;


/**
 * Created by Tomek on 04-Feb-17.
 */
public class ShoppingCart {

    private Map<Product, Integer> products = new HashMap<>();
    private String currency;


    public ShoppingCart(Map<Product, Integer> products, String currency) {
        this.products = products;
        this.currency = currency;
    }

    public Money getTotal() {
        Money totalPrice = new Money(0, currency);
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            totalPrice = totalPrice.add(entry.getKey().getPrice().multiplyBy(entry.getValue()));
        }
        return totalPrice;
    }

    public Map<Product, Integer> getProducts() {
        return Collections.unmodifiableMap(products);
    }

}
