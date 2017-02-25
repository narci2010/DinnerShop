package com.dinner.facade;

import com.dinner.model.business.Product;
import com.dinner.repository.ProductsRepository;
import com.dinner.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Tomek on 04-Feb-17.
 */
public class ShoppingCartFacadeOnShoppingCartService implements ShoppingCartFacade {
    @Autowired
    ProductsRepository productRepository;

    @Autowired
    ShoppingCartService shoppingCartService;

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
        return shoppingCartService.getProducts();
    }

    @Override
    public Double getTotalPrice() {
        return shoppingCartService.getTotal();
    }

    @Override
    public void clearShoppingCart() {
        shoppingCartService.clear();
    }
}
