package com.epam.passwordManagerMVC.exception;

public class RecordAlreadyExistsException extends Exception {
    public RecordAlreadyExistsException() {
        super("Oops! Record already exists for url!!");
    }
}
