package ru.coffeemachine.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CoffeeTaskNotFoundException extends EntityNotFoundException {
    public CoffeeTaskNotFoundException() {
    }

    public CoffeeTaskNotFoundException(String message) {
        super(message);
    }

    public CoffeeTaskNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CoffeeTaskNotFoundException(Throwable cause) {
        super(cause);
    }
}
