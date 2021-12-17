package com.epam.passwordManagerMVC.exception;


public class RecordDoesNotExistsException extends Exception {
    public RecordDoesNotExistsException() {
        super("Oops! No Record Found!!!");
    }
}
