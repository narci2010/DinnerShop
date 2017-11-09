package com.dinner.controller;

import com.dinner.model.domain.order.Order;
import com.dinner.model.transfer.ShoppingCartDTO;
import com.dinner.repository.UserOrdersRepository;
import com.dinner.service.application.implementations.DinnerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @RequestMapping(value = "/orders", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public String purchase(@ModelAttribute ShoppingCartDTO shoppingCart, Model model) {
        dinnerOrderService.placeOrder(shoppingCart);
        model.addAttribute("lastOrder", shoppingCart);

        return "confirm";
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String getOrders(){
        //Todo orders view
        return "elo";
    }

    @RequestMapping(value = "/orders/{orderId}", method = RequestMethod.GET)
    @ResponseBody
    public Order getOrder(@PathVariable(name = "orderId") Long orderId) {
        return ordersRepository.findOne(orderId);
    }


    @RequestMapping("/_ah/health")
    public String healthy() {
        // Message body required though ignored
        return "Still surviving.";
    }
}
