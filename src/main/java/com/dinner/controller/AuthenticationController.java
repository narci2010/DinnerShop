package com.dinner.controller;

import com.dinner.model.security.DinnerUser;
import com.dinner.service.DinnerUserService;
import com.dinner.service.exception.EmailExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Tomek on 05-Feb-17.
 */
@Controller
public class AuthenticationController {

    @Autowired
    DinnerUserService dinnerUserService;

    @RequestMapping(value = "/login")
    public String login(Model model) {
        return "login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        return "register";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        return "redirect:/login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute DinnerUser registerUser) {
        try {
            dinnerUserService.registerNewUserAccount(registerUser);
        } catch (EmailExistException e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/";
    }
}
