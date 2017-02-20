package com.dinner.facade;

import com.dinner.model.business.Product;

import java.util.List;

/**
 * Created by Tomek on 04-Feb-17.
 */
public interface ShoppingCartFacade {
    public boolean addToShoppingCart(Long productId);
    public boolean removeFromShoppingCart(Long productId);

    List<Product> getProducts();
}
