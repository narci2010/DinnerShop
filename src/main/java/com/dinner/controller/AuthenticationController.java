package com.dinner.controller;

import com.dinner.model.domain.user.DinnerUserDTO;
import com.dinner.service.application.interfaces.DinnerUserService;
import com.dinner.service.exception.EmailExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Tomek on 05-Feb-17.
 */
@Controller
public class AuthenticationController {

    @Autowired
    private DinnerUserService dinnerUserService;

    @RequestMapping(value = "/login")
    public String login() {
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
    public String register(@ModelAttribute DinnerUserDTO registerUser) throws EmailExistException {
        dinnerUserService.registerNewUserAccount(registerUser);
        return "redirect:/";
    }
}
