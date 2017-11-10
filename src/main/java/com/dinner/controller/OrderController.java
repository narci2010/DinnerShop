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

@Controller
public class OrderController {

    private final UserOrdersRepository ordersRepository;
    private final DinnerOrderService dinnerOrderService;


    @Autowired
    public OrderController(UserOrdersRepository ordersRepository, DinnerOrderService dinnerOrderService) {
        this.ordersRepository = ordersRepository;
        this.dinnerOrderService = dinnerOrderService;
    }


    @RequestMapping(value = "/orders", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public String purchase(@ModelAttribute ShoppingCartDTO shoppingCart, Model model) {
        dinnerOrderService.placeOrder(shoppingCart);
        model.addAttribute("lastOrder", shoppingCart);

        return "confirm";
    }

    @RequestMapping(value = "/orders/users/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String getOrders() {
        //Todo orders view
        return "elo";
    }

    @RequestMapping(value = "/orders/{orderId}", method = RequestMethod.GET)
    @ResponseBody
    public Order getOrder(@PathVariable(name = "orderId") Long orderId) {
        return ordersRepository.findOne(orderId);
    }


}
