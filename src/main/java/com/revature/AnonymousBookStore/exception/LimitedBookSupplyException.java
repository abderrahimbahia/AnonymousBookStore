package com.revature.AnonymousBookStore.exception;

public class LimitedBookSupplyException extends Exception{
    public LimitedBookSupplyException (String message){
        super(message);
    }
}
