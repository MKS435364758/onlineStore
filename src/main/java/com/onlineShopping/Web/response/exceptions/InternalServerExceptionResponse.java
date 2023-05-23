package com.onlineShopping.Web.response.exceptions;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Setter
@Getter
@RequiredArgsConstructor
public class InternalServerExceptionResponse {

    @NonNull
    private String message;

    @NonNull
    private Instant timestamp;


}
