package com.dinner.controller;

import com.dinner.model.domain.order.Order;
import com.dinner.model.transfer.ShoppingCartDTO;
import com.dinner.repository.UserOrdersRepository;
import com.dinner.service.application.implementations.DinnerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Tomek on 23-Jan-17.
 */
@Controller
public class MainController {


    private final UserOrdersRepository ordersRepository;

    private final DinnerOrderService dinnerOrderService;

    @Autowired
    public MainController(UserOrdersRepository ordersRepository, DinnerOrderService dinnerOrderService) {
        this.ordersRepository = ordersRepository;
        this.dinnerOrderService = dinnerOrderService;
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
    public String purchase(@ModelAttribute ShoppingCartDTO shoppingCart) {
        Order order = dinnerOrderService.placeOrder(shoppingCart);
        System.out.println(shoppingCart);
        System.out.println(order);
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
