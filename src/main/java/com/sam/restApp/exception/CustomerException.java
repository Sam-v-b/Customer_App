package com.sam.restApp.exception;

public class CustomerException extends  Exception{
    public CustomerException(){
        super("This is a customer exception");
    }
    public CustomerException(String message){
        super(message);
    }
}
