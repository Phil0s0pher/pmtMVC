package com.epam.passwordManagerMVC.service;

import com.epam.passwordManagerMVC.dao.AccountDaoImpl;
import com.epam.passwordManagerMVC.dto.LoginDTO;
import com.epam.passwordManagerMVC.dto.RegisterDTO;
import com.epam.passwordManagerMVC.entity.Account;
import com.epam.passwordManagerMVC.exception.AccountAlreadyExistsException;
import com.epam.passwordManagerMVC.exception.AccountDoesNotExistsException;
import com.epam.passwordManagerMVC.exception.UnableToRegisterAccount;
import com.epam.passwordManagerMVC.exception.WrongPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountDaoImpl accountDaoImpl;

    @Override
    public Account registerAccount(RegisterDTO registerDTO) throws UnableToRegisterAccount, AccountAlreadyExistsException {
        return accountDaoImpl.registerAccount(registerDTO);
    }

    @Override
    public String validateLogin(LoginDTO loginDTO) throws AccountDoesNotExistsException, WrongPasswordException {
        return accountDaoImpl.validateLogin(loginDTO);
    }
}