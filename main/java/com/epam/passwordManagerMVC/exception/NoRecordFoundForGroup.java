package com.epam.passwordManagerMVC.exception;

public class NoRecordFoundForGroup extends Exception{
    public NoRecordFoundForGroup(){
        super("Oops! No Record found for group");
    }
}

