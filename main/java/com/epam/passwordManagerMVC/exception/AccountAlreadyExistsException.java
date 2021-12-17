package com.epam.passwordManagerMVC.exception;

public class AccountAlreadyExistsException extends Exception {
    public AccountAlreadyExistsException() {
        super("Oops! Account already exists!!");
    }
}

