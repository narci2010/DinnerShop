package com.dinner.factory.order;

import com.dinner.annotations.domain.DomainFactory;
import com.dinner.model.domain.ShoppingCart;
import com.dinner.model.domain.order.Order;
import com.dinner.model.domain.product.Product;
import com.dinner.model.security.AuthenticationFacade;
import com.dinner.model.value.objects.Money;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.Map;

@DomainFactory
public class OrderFactory {
    @Autowired
    private AuthenticationFacade authenticationFacade;

    public Order createOrder(ShoppingCart shoppingCart) {
        Order order = new Order(shoppingCart.getTotal(), new Date(), authenticationFacade.getAuthentication());

        if (userCanAfford(shoppingCart.getTotal())) {
            addProductsToOrder(shoppingCart.getProducts(), order);
        }

        return order;

    }

    private boolean userCanAfford(Money totalPrice) {
        if (authenticationFacade.getAuthentication()
                .getUserMoney().greaterThan(totalPrice)) {
            return true;
        }

        return false;
    }

    private void addProductsToOrder(Map<Product, Integer> productsQuantity, Order order) {
        productsQuantity.forEach(order::addProductToOrder);
    }
}
