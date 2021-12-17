package com.epam.passwordManagerMVC.service;

import com.epam.passwordManagerMVC.dto.LoginDTO;
import com.epam.passwordManagerMVC.dto.RegisterDTO;
import com.epam.passwordManagerMVC.entity.Account;
import com.epam.passwordManagerMVC.exception.AccountAlreadyExistsException;
import com.epam.passwordManagerMVC.exception.AccountDoesNotExistsException;
import com.epam.passwordManagerMVC.exception.UnableToRegisterAccount;
import com.epam.passwordManagerMVC.exception.WrongPasswordException;

public interface AccountService {
    Account registerAccount(RegisterDTO registerDTO) throws UnableToRegisterAccount, AccountAlreadyExistsException;

    String validateLogin(LoginDTO loginDTO) throws AccountDoesNotExistsException, WrongPasswordException;
}

