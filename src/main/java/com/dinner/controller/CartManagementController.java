package com.dinner.controller;

import com.dinner.model.domain.product.Product;
import com.dinner.model.security.AuthenticationFacade;
import com.dinner.model.value.objects.Money;
import com.dinner.service.application.implementations.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * Created by Tomek on 04-Feb-17.
 */
@RestController
@Scope("request")
public class CartManagementController {
    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private AuthenticationFacade authenticationFacade;

    @RequestMapping(value = "/shoppingCart/products/{productId}", method = RequestMethod.POST)
    public boolean addToShoppingCart(@PathVariable(value = "productId") Long productId) {
        return  shoppingCartService.addToShoppingCart(productId, 1);
    }

    @RequestMapping(value = "/shoppingCart/products/{productId}/quantity/{number}", method = RequestMethod.PUT)
    public boolean updateProductInShoppingCart(@PathVariable(value = "productId") Long productId,
                                               @PathVariable(value = "number") Integer number) {
        return shoppingCartService.addToShoppingCart(productId, number);
    }

    @RequestMapping(value = "/shoppingCart/products/{productId}/", method = RequestMethod.DELETE)
    public boolean removeItemFromCart(@PathVariable("productId") Long productId) {
        return shoppingCartService.removeFromShoppingCart(productId);
    }

    @RequestMapping(value = "/shoppingCart/products/", method = RequestMethod.GET)
    public Map<Product, Integer> getProductsInShoppingCart() {
        return shoppingCartService.getProducts();
    }

    @RequestMapping(value = "/shoppingCart/totalPrice", method = RequestMethod.GET)
    public Money getTotalPrice() {
        return shoppingCartService.getTotal();
    }

    @RequestMapping(value = "user/money", method = RequestMethod.GET)
    public Money userMoney() {
        return authenticationFacade.getAuthentication().displayUserMoney();
    }
}
