package com.dinner.controller;

import com.dinner.facade.interfaces.PurchaseFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Tomek on 23-Jan-17.
 */
@Controller
public class MainController {

    @Autowired
    private PurchaseFacade purchaseFacade;

    @RequestMapping(value = "/")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/nav")
    public String navbar() {
        return "nav";
    }

    @RequestMapping(value = "/contact")
    public String contact() {
        return "contact";
    }

    @RequestMapping(value = "/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    @RequestMapping(value = "/purchase", method = RequestMethod.POST)
    public String purchase() {
        purchaseFacade.purchaseProducts();
        return "confirm";
    }
}
