package com.onlineShopping.Web.tools;

import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UnPackOptional<T> {

    public static <T> T getObject(Optional<T> object) {
        return object.orElse(null);
    }

}
