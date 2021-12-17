package com.epam.passwordManagerMVC.controller;

import com.epam.passwordManagerMVC.dto.LoginDTO;
import com.epam.passwordManagerMVC.dto.RegisterDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UrlRedirectController {
    @GetMapping(value = "/")
    public ModelAndView goToHome() {
        return new ModelAndView("login", "loginDTO", new LoginDTO());
//        return new ModelAndView("login");
    }

    @GetMapping(value = "/login")
    public ModelAndView goToLogin() {
        return new ModelAndView("login", "loginDTO", new LoginDTO());
    }

    @GetMapping(value = "/register")
    public ModelAndView goToRegistration() {
        return new ModelAndView("register", "registerDTO", new RegisterDTO());
    }
}
