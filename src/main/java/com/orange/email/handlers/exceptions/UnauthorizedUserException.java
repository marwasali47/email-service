package com.orange.email.handlers.exceptions;

import org.springframework.http.HttpStatus;

/**
 * Author: Muhammad Mustafa
 * Email: mumusta3.14@gmail.com
 * Created: 10/04/2022
 **/

public class UnauthorizedUserException extends ApiBaseException {

    public UnauthorizedUserException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatusCode() {
        return HttpStatus.UNAUTHORIZED;
    }
}
