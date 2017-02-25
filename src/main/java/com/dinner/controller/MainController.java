package com.dinner.controller;

import com.dinner.facade.PurchaseFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Tomek on 23-Jan-17.
 */
@Controller
public class MainController {

    @Autowired
    PurchaseFacade purchaseFacade;

    @RequestMapping(value = "/")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/nav")
    public String navbar() {
        return "nav";
    }

    @RequestMapping(value = "/purchase", method = RequestMethod.POST)
    public String purchase() {
        purchaseFacade.purchaseProducts();

        return "/confirmation";
    }
}
