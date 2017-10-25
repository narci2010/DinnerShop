package com.dinner.facade.implementations;

import com.dinner.facade.interfaces.ShoppingCartFacade;
import com.dinner.model.domain.product.Product;
import com.dinner.repository.ProductsRepository;
import com.dinner.service.application.implementations.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomek on 04-Feb-17.
 */
public class ShoppingCartFacadeOnShoppingCartService implements ShoppingCartFacade {
    @Autowired
    private ProductsRepository productRepository;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Override
    public boolean addToShoppingCart(Long productId) {
        return shoppingCartService.addToShoppingCart(productRepository.getOne(productId));
    }

    @Override
    public boolean removeFromShoppingCart(Long productId) {
        return shoppingCartService.removeFromShoppingCart(productRepository.getOne(productId));
    }

    @Override
    public List<Product> getProducts() {
        return new ArrayList<>();
    }

    @Override
    public Double getTotalPrice() {
        return Double.valueOf(10);
    }

    @Override
    public void clearShoppingCart() {
        shoppingCartService.clear();
    }
}
