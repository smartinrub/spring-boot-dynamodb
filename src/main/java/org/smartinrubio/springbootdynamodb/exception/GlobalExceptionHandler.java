package org.smartinrubio.springbootdynamodb.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Internal Error")
    @ExceptionHandler(GenericDynamoDBException.class)
    public void genericDynamoDBExceptionHandler() { }

    @ResponseStatus(value = HttpStatus.CONFLICT, reason = "Table Already Exists")
    @ExceptionHandler(DuplicateTableException.class)
    public void duplicateTableExceptionHandler() { }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Internal Error")
    @ExceptionHandler(IOException.class)
    public void iOExceptionHandler() { }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Hotel Not Found")
    @ExceptionHandler(HotelNotFoundException.class)
    public void hotelNotFoundExceptionHandler() { }

}
