package com.kc.mylearnings.springmvc.controller;

import com.kc.mylearnings.springmvc.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.logging.Logger;

@Controller
public class LoginController {

    Logger LOGGER = Logger.getLogger(LoginController.class.toString());

    @Autowired
    LoginService loginService;

    @RequestMapping(value = "/login1", method = RequestMethod.GET)
    //@ResponseBody //No need to print a text message on response body instead we need dispatcher to send jsp (ViewResolver) response.
    public String showLoginPage(){
        System.out.println("LoginController - Get method");
        LOGGER.info("LoginController - Get method call");
        return "login1";
    }

    @RequestMapping(value = "/login1", method = RequestMethod.POST)
    //@ResponseBody
    public String handleLoginRequest(@RequestParam String name, @RequestParam String password, ModelMap modelMap){
        System.out.println("LoginController - POST method");
        LOGGER.info("LoginController - POST method call - Welcome " + name);
        modelMap.put("name", name);
        modelMap.put("password", password);

        if(!loginService.validateUser(name, password)) {
            modelMap.put("errorMessage", "Invalid credentials");
            return "login1";
        }
        return "welcome";
        //return "Welcome from controller";
    }
}
