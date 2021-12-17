package com.epam.passwordManagerMVC.exception;

public class UnableToRegisterAccount extends Exception{
    public UnableToRegisterAccount(){
        super("Unable to register!! Try again");
    }
}
