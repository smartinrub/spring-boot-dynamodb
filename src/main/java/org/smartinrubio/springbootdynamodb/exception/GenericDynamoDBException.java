package org.smartinrubio.springbootdynamodb.exception;

public class GenericDynamoDBException extends RuntimeException {
    public GenericDynamoDBException(Exception e) {
        super(e);
    }
}
