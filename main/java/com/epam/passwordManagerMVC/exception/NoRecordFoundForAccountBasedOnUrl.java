package com.epam.passwordManagerMVC.exception;

public class NoRecordFoundForAccountBasedOnUrl extends Exception {
    public NoRecordFoundForAccountBasedOnUrl() {
        super("Oops! No Record Found For Input Url!!");
    }
}

