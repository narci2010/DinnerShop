package com.dinner.serivce;

import com.dinner.model.business.Account;
import com.dinner.model.business.Product;
import com.dinner.model.business.ShoppingCart;
import com.dinner.model.security.AuthenticatedUser;
import com.dinner.model.security.AuthenticationFacade;
import com.dinner.service.ShoppingCartService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

/**
 * Created by Tomek on 04-Feb-17.
 */

public class ShoppingCartServiceTest {
    @InjectMocks
    ShoppingCartService shoppingCartService;
    @Mock
    AuthenticatedUser user;
    @Mock
    Account account;
    @Mock
    ShoppingCart shoppingCart;

    @Mock
    AuthenticationFacade authenticationFacade;
    @Mock
    SecurityContextHolder securityContextHolder;
    @Mock
    SecurityContext securityContext;
    @Mock
    Authentication authentication;
    @Mock
    Principal principal;



    private Product product;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        product = new Product("desc", 20.00, new byte[1]);
        when(user.getAccount()).thenReturn(account);
        when(authenticationFacade.getAuthentication()).thenReturn(user);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(user);
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    public void addToCartTestWithoutMoney() {
        when(user.getAccount().withdraw(product.getPrice())).thenReturn(false);
        boolean response = shoppingCartService.addToShoppingCart(product);
        Assert.assertFalse(response);
    }
    @Test
    public void addToCartTestEnoughMoney(){
        when(user.getAccount().withdraw(product.getPrice())).thenReturn(true);
        boolean response = shoppingCartService.addToShoppingCart(product);
        Assert.assertTrue(response);

    }

    @Test
    public void getListOfProducts(){
        List<Product> products = new ArrayList<>(2);
        products.add(new Product());
        products.add(new Product());
        when(shoppingCart.getProducts()).thenReturn(products);
        Assert.assertEquals(2,shoppingCartService.getProducts().size());

    }
}
