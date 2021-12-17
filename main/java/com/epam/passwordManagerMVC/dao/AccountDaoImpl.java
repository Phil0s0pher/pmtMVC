package com.epam.passwordManagerMVC.dao;

import com.epam.passwordManagerMVC.converter.Convert;
import com.epam.passwordManagerMVC.dto.LoginDTO;
import com.epam.passwordManagerMVC.dto.RegisterDTO;
import com.epam.passwordManagerMVC.entity.Account;
import com.epam.passwordManagerMVC.exception.AccountAlreadyExistsException;
import com.epam.passwordManagerMVC.exception.AccountDoesNotExistsException;
import com.epam.passwordManagerMVC.exception.WrongPasswordException;
import com.epam.passwordManagerMVC.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountDaoImpl implements AccountDao {
    @Autowired
    AccountRepository accountRepository;

    @Override
    public Account registerAccount(RegisterDTO registerDTO) throws AccountAlreadyExistsException {
        Account accountConverted = Convert.convertToEntity(registerDTO);
        boolean existAccount = accountRepository.existsByUserName(accountConverted.getUserName());
        if (existAccount) {
            throw new AccountAlreadyExistsException();
        }
        accountConverted.setPassword((accountConverted.getPassword()));
        return accountRepository.save(accountConverted);
    }

    @Override
    public String validateLogin(LoginDTO loginDTO) throws AccountDoesNotExistsException, WrongPasswordException {
        Account account = Convert.convertToEntity(loginDTO);
        account.setPassword((account.getPassword()));
        Account accountByUserName = accountRepository.findByUserName(account.getUserName());
        if (accountByUserName == null) {
            throw new AccountDoesNotExistsException();
        } else {
            if (accountByUserName.getPassword().equals(account.getPassword())) {
                return accountByUserName.getAccountName();
            } else {
                throw new WrongPasswordException();
            }
        }
    }
}
