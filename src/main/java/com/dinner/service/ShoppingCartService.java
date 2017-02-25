package com.dinner.service;

import com.dinner.model.business.Product;
import com.dinner.model.business.ShoppingCart;
import com.dinner.model.security.AuthenticatedUser;
import com.dinner.model.security.AuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Tomek on 04-Feb-17.
 */
@Component
@Scope("session")
public class ShoppingCartService {

    @Autowired
    ShoppingCart shoppingCart;

    @Autowired
    AuthenticationFacade authenticationFacade;

    public boolean addToShoppingCart(Product product) {
        if (userHaveEnoughMoney(product)) {
            shoppingCart.addProduct(product);
            return true;
        }
        return false;
    }

    public boolean removeFromShoppingCart(Product product) {
        if (shoppingCart.hasProduct(product)) {
            shoppingCart.removeProduct(product);
            getCurrentlyLogInUser().getAccount().returnCash(product.getPrice());
            return true;
        }
        return false;
    }

    public List<Product> getProducts() {
        return shoppingCart.getProducts();
    }

    public Double getTotal() {
        return shoppingCart.getTotal();
    }

    public void clear(){
        shoppingCart.clear();
    }

    private boolean userHaveEnoughMoney(Product product) {
        return getCurrentlyLogInUser().getAccount().withdraw(product.getPrice());
    }

    private AuthenticatedUser getCurrentlyLogInUser() {
        return authenticationFacade.getAuthentication();
    }


}
