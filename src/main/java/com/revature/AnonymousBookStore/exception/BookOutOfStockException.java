package com.revature.AnonymousBookStore.exception;

public class BookOutOfStockException extends Exception{
    public BookOutOfStockException(String message){
        super(message);
    }
}
