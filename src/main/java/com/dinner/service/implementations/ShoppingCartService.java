package com.dinner.service.implementations;

import com.dinner.model.domain.Product;
import com.dinner.model.domain.ShoppingCart;
import com.dinner.model.domain.user.User;
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
    private ShoppingCart shoppingCart;

    @Autowired
    private AuthenticationFacade authenticationFacade;

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
            //getCurrentlyLogInUser().getAccount().returnCash(product.getPrice());
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
//        return getCurrentlyLogInUser().getAccount().withdraw(product.getPrice());
        return true;
    }

    private User getCurrentlyLogInUser() {
        return authenticationFacade.getAuthentication();
    }


}
