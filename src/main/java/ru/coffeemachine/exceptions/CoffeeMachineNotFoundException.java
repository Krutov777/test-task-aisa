package ru.coffeemachine.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CoffeeMachineNotFoundException extends EntityNotFoundException {
    public CoffeeMachineNotFoundException() {
        super();
    }

    public CoffeeMachineNotFoundException(String message) {
        super(message);
    }

    public CoffeeMachineNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CoffeeMachineNotFoundException(Throwable cause) {
        super(cause);
    }
}
