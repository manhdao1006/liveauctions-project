package com.ute.auction.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ResourceNotFormatException extends RuntimeException {

    public ResourceNotFormatException(String message) {
        super(message);
    }

}
