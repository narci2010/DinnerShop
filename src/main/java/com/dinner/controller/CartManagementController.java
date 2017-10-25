package com.dinner.controller;

import com.dinner.facade.interfaces.ShoppingCartFacade;
import com.dinner.model.domain.product.Product;
import com.dinner.model.security.AuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * Created by Tomek on 04-Feb-17.
 */
@RestController
@Scope("request")
public class CartManagementController {
    @Autowired
    private ShoppingCartFacade shoppingCartFacade;

    @Autowired
    private AuthenticationFacade authenticationFacade;

    @RequestMapping(value = "/addToCart", method = RequestMethod.POST)
    public boolean addToShoppingCart(@RequestParam(value = "productId") Long productId) {
        return shoppingCartFacade.addToShoppingCart(productId);
    }

    @RequestMapping(value = "/removeItem", method = RequestMethod.POST)
    public boolean removeItemFromCart(@RequestParam("productId") Long productId) {
        return shoppingCartFacade.removeFromShoppingCart(productId);
    }

    @RequestMapping(value = "/shoppingCart", method = RequestMethod.GET)
    public List<Product> getProductsInShoppingCart() {
        return shoppingCartFacade.getProducts();
    }

    @RequestMapping(value = "/totalPrice")
    public Double getTotalPrice() {
        return shoppingCartFacade.getTotalPrice();
    }

    @RequestMapping(value = "/userMoney")
    public Double userMoney() {
/*
        return authenticationFacade.getAuthentication().getAccount().getMoney();
*/
        return 200.00;
    }
}
