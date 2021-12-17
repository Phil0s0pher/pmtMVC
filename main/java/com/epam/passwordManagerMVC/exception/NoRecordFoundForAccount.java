package com.epam.passwordManagerMVC.exception;

public class NoRecordFoundForAccount extends Exception {
    public NoRecordFoundForAccount(){
        super("Oops! No Record Found!!");
    }
}
