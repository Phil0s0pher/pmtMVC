package com.epam.passwordManagerMVC.exception;

public class NoGroupFoundForAccount extends Exception{
    public NoGroupFoundForAccount(){
        super("Oops! No group found!!");
    }
}