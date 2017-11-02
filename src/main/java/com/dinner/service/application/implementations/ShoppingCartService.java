package com.dinner.service.application.implementations;

import com.dinner.model.domain.product.Product;
import com.dinner.model.domain.ShoppingCart;
import com.dinner.model.domain.user.User;
import com.dinner.model.security.AuthenticationFacade;
import com.dinner.model.value.objects.Money;
import com.dinner.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

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

    @Autowired
    private ProductsRepository productsRepository;

    public boolean addToShoppingCart(Long productId, Integer quantity) {
        Product product = productsRepository.getOne(productId);
        if (userCanAfford(product, quantity)) {
            shoppingCart.addProduct(product, quantity);
            return true;
        }
        return false;
    }

    public boolean removeFromShoppingCart(Long productId) {
        Product product = productsRepository.getOne(productId);

        if (shoppingCart.hasProduct(product)) {
            Money price = shoppingCart.productPriceInShoppingCart(product);
            shoppingCart.removeProduct(product);
            getCurrentlyLogInUser().returnMoney(price);
            return true;
        }
        return false;
    }

    public Map<Product, Integer> getProducts() {
        return shoppingCart.getProducts();
    }

    public Money getTotal() {
        return shoppingCart.getTotal();
    }

    public void clear() {
        shoppingCart.clear();
    }

    private boolean userCanAfford(Product product, Integer quantity) {
        Money price = product.getPrice().multiplyBy(quantity);
        return getCurrentlyLogInUser().withdraw(price);
    }

    private User getCurrentlyLogInUser() {
        return authenticationFacade.getAuthentication();
    }


}
