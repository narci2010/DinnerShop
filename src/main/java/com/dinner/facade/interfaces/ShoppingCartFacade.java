package com.dinner.facade.interfaces;

import com.dinner.model.domain.product.Product;

import java.util.List;

/**
 * Created by Tomek on 04-Feb-17.
 */
public interface ShoppingCartFacade {
    boolean addToShoppingCart(Long productId);

    boolean removeFromShoppingCart(Long productId);

    List<Product> getProducts();

    Double getTotalPrice();

    void clearShoppingCart();
}
