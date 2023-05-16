package com.onlineShopping.Web.object;

import java.util.Optional;

public class Unwarranted<T> {

    public static <T> T getObject(Optional<T> object) {
        return object.orElse(null);
    }

}
