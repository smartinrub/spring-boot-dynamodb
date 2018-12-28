package org.smartinrubio.springbootdynamodb.exception;

public class DuplicateTableException extends RuntimeException {
    public DuplicateTableException(Exception e) {
        super(e);
    }
}
