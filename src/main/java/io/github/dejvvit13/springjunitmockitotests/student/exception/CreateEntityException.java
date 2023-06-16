package io.github.dejvvit13.springjunitmockitotests.student.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CreateEntityException extends RuntimeException {
    public CreateEntityException(String message) {
        super(message);
    }
}
