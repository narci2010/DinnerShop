package com.dinner.controller;

import com.dinner.model.security.AuthenticatedUser;
import com.dinner.model.security.AuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Tomek on 23-Jan-17.
 */
@Controller
public class MainController {

    @Autowired
    AuthenticationFacade authenticationFacade;

    @RequestMapping(value = "/")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/nav")
    public String navbar() {
        return "nav";
    }

    @RequestMapping(value = "/userMoney")
    @ResponseBody
    public Double userMoney() {
        return authenticationFacade.getAuthentication().getAccount().getMoney();
    }
}
