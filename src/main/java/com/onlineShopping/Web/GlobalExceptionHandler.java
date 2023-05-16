package com.onlineShopping.Web;

import com.onlineShopping.Web.response.UserSignErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.xml.bind.ValidationException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<UserSignErrorResponse> handleUserValidationException(ValidationException ex){
        return new ResponseEntity<>( new UserSignErrorResponse(ex.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }


}
