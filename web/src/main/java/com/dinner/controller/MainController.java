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

    @RequestMapping("/_ah/health")
    public String healthy() {
        // Message body required though ignored
        return "Still surviving.";
    }

    @RequestMapping(value = "/map")
    public String map(){
        return "map";
    }
}
