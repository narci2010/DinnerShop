package com.dinner;

import com.dinner.facade.implementations.DinnerPurchaseFacade;
import com.dinner.facade.interfaces.PurchaseFacade;
import com.dinner.facade.interfaces.ShoppingCartFacade;
import com.dinner.facade.implementations.ShoppingCartFacadeOnShoppingCartService;
import com.dinner.model.security.AuthenticationFacade;
import com.dinner.model.security.AuthenticationFacadeImpl;
import com.dinner.service.implementations.DinnerAccountService;
import com.dinner.service.implementations.DinnerOrderService;
import com.dinner.service.implementations.ShoppingCartService;
import com.dinner.service.interfaces.AccountService;
import com.dinner.service.interfaces.OrderService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class DinnerApplication {
    @Bean
    public ShoppingCartFacade shoppingCartFacade() {
        return new ShoppingCartFacadeOnShoppingCartService();
    }

    @Bean
    public ShoppingCartService shoppingCartService() {
        return new ShoppingCartService();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(4);
    }

    @Bean
    @Lazy
    public AuthenticationFacade authenticationFacade() {
        return new AuthenticationFacadeImpl();
    }

    @Bean
    public OrderService orderService() {
        return new DinnerOrderService();
    }

    @Bean
    public AccountService accountService() {
        return new DinnerAccountService();
    }

    @Bean
    public PurchaseFacade purchaseFacade(){
        return new DinnerPurchaseFacade();
    }

    public static void main(String[] args) {
        SpringApplication.run(DinnerApplication.class, args);
    }
}
