package com.onlineShopping.Web;

import com.onlineShopping.Web.exception.DataNotFound;
import com.onlineShopping.Web.exception.InternalServerException;
import com.onlineShopping.Web.response.exceptions.DataNoFoundResponse;
import com.onlineShopping.Web.response.exceptions.InternalServerExceptionResponse;
import com.onlineShopping.Web.response.exceptions.NoFoundExceptionResponse;
import com.onlineShopping.Web.response.users.UserSignUpErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.xml.bind.ValidationException;
import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<UserSignUpErrorResponse> handleUserValidationException(ValidationException ex){
        return new ResponseEntity<>( new UserSignUpErrorResponse(ex.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(DataNotFound.class)
    public ResponseEntity<NoFoundExceptionResponse> handlerUserNoFoundException(DataNotFound ex){
        return new ResponseEntity<>(new DataNoFoundResponse(ex.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }


    @ExceptionHandler(InternalServerException.class)
    public ResponseEntity<InternalServerExceptionResponse> handlerInternalServerException(InternalServerException ex){
        return new ResponseEntity<>(new InternalServerExceptionResponse(ex.getMessage(), Instant.now()),
                HttpStatus.NOT_FOUND
        );
    }

}
