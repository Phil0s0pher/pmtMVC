package com.epam.passwordManagerMVC.exception;

public class AccountDoesNotExistsException extends Exception {
    public AccountDoesNotExistsException() {
        super("Oops! No Account Found!!");
    }
}