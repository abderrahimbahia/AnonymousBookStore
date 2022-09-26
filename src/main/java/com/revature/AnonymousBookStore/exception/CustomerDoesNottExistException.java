package com.revature.AnonymousBookStore.exception;

import com.revature.AnonymousBookStore.entity.Customer;

public class CustomerDoesNottExistException extends Exception {
    public CustomerDoesNottExistException(String message){
        super(message);
    }
}
