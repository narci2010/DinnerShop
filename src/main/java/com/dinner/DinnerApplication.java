package com.dinner;

import com.dinner.facade.ShoppingCartFacade;
import com.dinner.facade.ShoppingCartFacadeOnShoppingCartService;
import com.dinner.model.business.ShoppingCart;
import com.dinner.model.security.AuthenticatedUser;
import com.dinner.model.security.AuthenticationFacade;
import com.dinner.model.security.AuthenticationFacadeImpl;
import com.dinner.service.ShoppingCartService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class DinnerApplication {
    @Bean
    @Scope("session")
    public ShoppingCartFacade shoppingCartFacade() {
        return new ShoppingCartFacadeOnShoppingCartService();
    }

    @Bean
    public ShoppingCartService shoppingCartService() {
        return new ShoppingCartService();
    }

    @Bean
    public ShoppingCart shoppingCart() {
        return new ShoppingCart();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(4);
    }

    @Bean
    @Lazy
    public AuthenticationFacade authenticationFacade(){
        return new AuthenticationFacadeImpl();
    }
/*
    @Bean
    public AuthenticatedUser authenticatedUser(){
        return (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
*/

    public static void main(String[] args) {
        SpringApplication.run(DinnerApplication.class, args);
    }
}
