package com.dinner.serivce;


import com.dinner.model.domain.ShoppingCart;
import com.dinner.model.domain.order.Order;
import com.dinner.model.domain.product.Product;
import com.dinner.model.domain.user.Account;
import com.dinner.model.domain.user.User;
import com.dinner.model.security.AuthenticationFacade;
import com.dinner.model.value.objects.Money;
import com.dinner.service.application.implementations.ShoppingCartService;
import com.dinner.service.application.interfaces.OrderService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class DinnerOrderServiceTest {

    @Autowired
    private OrderService orderService;

    private Product product;

    @InjectMocks
    ShoppingCartService shoppingCartService;
    @Mock
    User user;
    @Mock
    Account account;
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

    @Mock
    ShoppingCart shoppingCart;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        product = new Product("desc", new Money(20.00,"PLN"), new byte[1]);
//        when(user.getAccount()).thenReturn(account);
        when(authenticationFacade.getAuthentication()).thenReturn(user);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(user);
        SecurityContextHolder.setContext(securityContext);
        Map<Product, Integer> map = new HashMap<>();
        map.put(product,1);
        when(shoppingCart.getProducts()).thenReturn(map);

        shoppingCart.addProduct(product,1);
    }

    @Test
    public void shouldPlaceOrder(){
        Order order = orderService.placeOrder(shoppingCart);
        System.out.println(order);
    }

}
