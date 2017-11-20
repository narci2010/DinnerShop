package com.dinner.controller;

import com.dinner.model.security.AuthenticationFacade;
import com.dinner.model.value.objects.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * Created by Tomek on 04-Feb-17.
 */
@RestController
public class CartManagementController {

    @Autowired
    private AuthenticationFacade authenticationFacade;

    @RequestMapping(value = "user/money", method = RequestMethod.GET)
    public Money userMoney() {
        return authenticationFacade.getAuthentication().getUserMoney();
    }
}
