package com.epam.passwordManagerMVC.controller;

import com.epam.passwordManagerMVC.dto.LoginDTO;
import com.epam.passwordManagerMVC.exception.AccountDoesNotExistsException;
import com.epam.passwordManagerMVC.exception.WrongPasswordException;
import com.epam.passwordManagerMVC.service.AccountServiceImpl;
import com.epam.passwordManagerMVC.service.GroupServiceImpl;
import com.epam.passwordManagerMVC.service.RecordServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(LoginController.class)
class LoginControllerTest {
    @Autowired
    MockMvc mvc;

    @MockBean
    AccountServiceImpl accountServiceImpl;

    @MockBean
    GroupServiceImpl groupServiceImpl;

    @MockBean
    RecordServiceImpl recordServiceImpl;

    @Test
    public void testLoginUserThrowAccountDoesNotExistExceptionForExistedUser() throws Exception {
        when(accountServiceImpl.validateLogin(any())).thenThrow(new AccountDoesNotExistsException());
        mvc.perform(post("/loginUser"))
                .andExpect(view().name("login"))
                .andExpect(model().attribute("error", "Oops! No Account Found!!"));
    }

    @Test
    public void testLoginUserThrowWrongPasswordExceptionForExistedUser() throws Exception {
        when(accountServiceImpl.validateLogin(any())).thenThrow(new WrongPasswordException());
        mvc.perform(post("/loginUser"))
                .andExpect(view().name("login"))
                .andExpect(model().attribute("error", "Oops! Password does not matched!!"));
    }

    @Test
    public void testLoginUserDoesNotThrowExceptionWhileValidationSucceed() throws Exception {
        LoginDTO loginDTO = new LoginDTO("KGR009517", "Tiger1234");
        when(accountServiceImpl.validateLogin(any())).thenReturn(any());
        mvc.perform(post("/loginUser").accept(MediaType.APPLICATION_JSON)
                        .flashAttr("loginDTO", loginDTO))
                .andExpect(view().name("redirect:/record"));
    }

    @Test
    public void testLoginUserReturnToLoginWhileValidationFailed() throws Exception {
        LoginDTO loginDTO = new LoginDTO("vishal kumar", "Vishal834019");
        mvc.perform(post("/loginUser").accept(MediaType.APPLICATION_JSON)
                        .flashAttr("loginDTO", loginDTO))
                .andExpect(view().name("login"));
    }
}
