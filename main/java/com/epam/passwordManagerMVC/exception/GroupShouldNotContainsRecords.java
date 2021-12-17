package com.epam.passwordManagerMVC.exception;

public class GroupShouldNotContainsRecords extends Exception {
    public GroupShouldNotContainsRecords(){
        super("Oops! Group should be empty before deletion!!");
    }
}

