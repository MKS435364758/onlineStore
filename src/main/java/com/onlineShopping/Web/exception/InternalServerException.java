package com.onlineShopping.Web.exception;

public class InternalServerException extends RuntimeException{

    public InternalServerException(String message){
        super(message);
    }

    public InternalServerException(){
        super("Internal Server Error");
    }

}
