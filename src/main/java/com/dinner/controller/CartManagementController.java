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
public class CartManagementController {

    @Autowired
    private AuthenticationFacade authenticationFacade;

    @RequestMapping(value = "user/money", method = RequestMethod.GET)
    public Money userMoney() {
        return authenticationFacade.getAuthentication().displayUserMoney();
    }
}
