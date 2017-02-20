package com.dinner.controller;
import com.dinner.facade.ShoppingCartFacade;
import com.dinner.model.business.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Created by Tomek on 04-Feb-17.
 */
@RestController
@Scope("request")
public class CartManagementController {
    @Autowired
    ShoppingCartFacade shoppingCartFacade;

    @RequestMapping(value = "/addToCart", method = RequestMethod.POST)
    public boolean addToShoppingCart(@RequestParam(value = "productId") Long productId) {
        return shoppingCartFacade.addToShoppingCart(productId);
    }

    @RequestMapping(value = "/removeItem", method = RequestMethod.POST)
    public boolean removeItemFromCart(@RequestParam("productId") Long productId) {
        return shoppingCartFacade.removeFromShoppingCart(productId);
    }

    @RequestMapping(value ="/shoppingCart", method = RequestMethod.GET)
    public List<Product> getProductsInShoppingCart(){
        return shoppingCartFacade.getProducts();
    }
}
