package com.epam.passwordManagerMVC.controller;

import com.epam.passwordManagerMVC.dto.LoginDTO;
import com.epam.passwordManagerMVC.dto.RegisterDTO;
import com.epam.passwordManagerMVC.entity.Account;
import com.epam.passwordManagerMVC.exception.AccountAlreadyExistsException;
import com.epam.passwordManagerMVC.exception.UnableToRegisterAccount;
import com.epam.passwordManagerMVC.service.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class RegisterController {

    @Autowired
    AccountServiceImpl accountServiceImpl;

    @PostMapping(value = "/registerUser")
    public ModelAndView registerUser(@Valid @ModelAttribute("registerDTO") RegisterDTO registerDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("register");
        }
        try {
            Account registeredAccount = accountServiceImpl.registerAccount(registerDTO);
            return new ModelAndView("login", "loginDTO", new LoginDTO())
                    .addObject("message", "User Registration Successfully!!");
        } catch (AccountAlreadyExistsException | UnableToRegisterAccount e) {
            return handleException(e);
        }
    }

    public ModelAndView handleException(Exception ex) {
        return new ModelAndView("register", "registerDTO", new RegisterDTO())
                .addObject("error", ex.getMessage());
    }
}
