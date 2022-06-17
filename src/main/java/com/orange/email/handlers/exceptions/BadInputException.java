package com.orange.email.handlers.exceptions;

import org.springframework.http.HttpStatus;

/**
 * Author: Muhammad Mustafa
 * Email: mumusta3.14@gmail.com
 * Created: 10/04/2022
 **/

public class BadInputException extends ApiBaseException {

    public BadInputException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatusCode() {
        return HttpStatus.BAD_REQUEST;
    }
}
