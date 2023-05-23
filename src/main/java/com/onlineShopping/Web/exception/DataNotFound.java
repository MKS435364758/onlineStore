package com.onlineShopping.Web.exception;

public class DataNotFound extends RuntimeException {

    public DataNotFound(String message) {
        super(message);
    }

    public DataNotFound() {
        super("No Data Found");
    }

}
