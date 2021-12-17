package com.epam.passwordManagerMVC.dao;

import com.epam.passwordManagerMVC.dto.LoginDTO;
import com.epam.passwordManagerMVC.dto.RegisterDTO;
import com.epam.passwordManagerMVC.entity.Account;
import com.epam.passwordManagerMVC.exception.AccountAlreadyExistsException;
import com.epam.passwordManagerMVC.exception.AccountDoesNotExistsException;
import com.epam.passwordManagerMVC.exception.WrongPasswordException;
import com.epam.passwordManagerMVC.repository.AccountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountDaoTest {
    @InjectMocks
    AccountDaoImpl accountDaoImpl;

    @Mock
    AccountRepository accountRepository;

    @Test
    void testRegisterAccountThrowExceptionWhenAccountAlreadyExists() {
        RegisterDTO registerDTO = new RegisterDTO("Scooby Doo", "KGR009517", "Tiger1234");
        when(accountRepository.existsByUserName(any())).thenReturn(true);
        Assertions.assertThrows(AccountAlreadyExistsException.class, () -> accountDaoImpl.registerAccount(registerDTO));
    }

    @Test
    void testRegisterAccountDoesNotThrowExceptionWhileNewAccountRegistration() {
        RegisterDTO registerDTO = new RegisterDTO("Scooby Doo", "KGR009517", "Tiger1234");
        Account account = new Account();
        when(accountRepository.save(any())).thenReturn(account);
        when(accountRepository.existsByUserName(any())).thenReturn(false);
        Assertions.assertDoesNotThrow(() -> accountDaoImpl.registerAccount(registerDTO));
    }


    @Test
    void validateLoginThrowExceptionWhenAccountDoesNotExist() {
        LoginDTO loginDTO = new LoginDTO("KGR009517", "Tiger1234");
        when(accountRepository.findByUserName(any())).thenReturn(null);
        Assertions.assertThrows(AccountDoesNotExistsException.class, () -> accountDaoImpl.validateLogin(loginDTO));
    }

    @Test
    void validateLoginThrowExceptionWhenWrongPasswordException() {
        LoginDTO loginDTO = new LoginDTO("KGR009517", "Tger1234");
        Account account = new Account("KGR009517", "Tiger123");
        when(accountRepository.findByUserName(any())).thenReturn(account);
        Assertions.assertThrows(WrongPasswordException.class, () -> accountDaoImpl.validateLogin(loginDTO));
    }

    @Test
    void validateLoginDoesNotThrowExceptionWhileAccountExist() {
        LoginDTO loginDTO = new LoginDTO("KGR009517", "Tiger1234");
        Account account = new Account("KGR009517", "Tiger1234");
        account.setPassword((account.getPassword()));
        when(accountRepository.findByUserName(any())).thenReturn(account);
        Assertions.assertDoesNotThrow(() -> accountDaoImpl.validateLogin(loginDTO));
    }

    @Test
    void validateLoginReturnAccountNameWhileAccountExist() throws AccountDoesNotExistsException, WrongPasswordException {
        LoginDTO loginDTO = new LoginDTO("KGR009517", "Tiger1234");
        Account account = new Account("Scooby Doo", "KGR009517", "Tiger1234");
        account.setPassword((account.getPassword()));
        when(accountRepository.findByUserName(any())).thenReturn(account);
        Assertions.assertEquals("Scooby Doo", accountDaoImpl.validateLogin(loginDTO));
    }
}
