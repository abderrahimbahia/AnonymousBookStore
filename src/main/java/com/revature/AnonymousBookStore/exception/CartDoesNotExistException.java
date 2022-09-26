package com.revature.AnonymousBookStore.exception;

public class CartDoesNotExistException extends Exception{
    public CartDoesNotExistException(String message){
        super(message);
    }
}
