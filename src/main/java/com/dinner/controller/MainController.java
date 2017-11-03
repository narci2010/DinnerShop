package com.dinner.controller;

import com.dinner.facade.interfaces.PurchaseFacade;
import com.dinner.model.domain.ShoppingCart;
import com.dinner.model.domain.order.Order;
import com.dinner.repository.UserOrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Tomek on 23-Jan-17.
 */
@Controller
public class MainController {


    private final PurchaseFacade purchaseFacade;
    private final UserOrdersRepository ordersRepository;

    @Autowired
    public MainController(PurchaseFacade purchaseFacade, UserOrdersRepository ordersRepository) {
        this.purchaseFacade = purchaseFacade;
        this.ordersRepository = ordersRepository;
    }

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

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public String purchase(@RequestBody ShoppingCart shoppingCart) {
        purchaseFacade.purchaseProducts();
        return "confirm";
    }

    @RequestMapping(value = "/order/{orderId}", method = RequestMethod.GET)
    @ResponseBody
    public Order getOrder(@PathVariable(name = "orderId") Long orderId) {
        return ordersRepository.getOne(orderId);
    }


    @RequestMapping("/_ah/health")
    public String healthy() {
        // Message body required though ignored
        return "Still surviving.";
    }
}
