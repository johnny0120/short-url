package com.solution.shorturl.exception;

public class GlobalException extends RuntimeException {

    private static final long serialVersionUID = 8471280490635933202L;

    private final ErrorModel errorModel;

    public GlobalException(ApplicationException cause) {
        super(cause);
        this.errorModel = cause.getErrorModel();
    }

    public ErrorModel getErrorModel() {
        return errorModel;
    }
}
