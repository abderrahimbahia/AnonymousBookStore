package com.revature.AnonymousBookStore.exception;

public class OrderLineDontExistException extends Exception{
    public OrderLineDontExistException(String message){
        super(message);
    }
}
