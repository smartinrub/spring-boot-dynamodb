package org.smartinrubio.springbootdynamodb.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(GenericDynamoDBException.class)
    public void genericDynamoDBExceptionHandler() {
    }

    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ExceptionHandler(DuplicateTableException.class)
    public void duplicateTableExceptionHandler() {
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(IOException.class)
    public void IOExceptionHandler() {

    }
}
