package com.solution.shorturl.exception;

public class ApplicationException extends Exception {

    private static final long serialVersionUID = -2738575789404945155L;

    private final ErrorModel errorModel;

    public ApplicationException(ErrorModel errorModel) {
        super(errorModel.toString());
        this.errorModel = errorModel;
    }

    public ErrorModel getErrorModel() {
        return errorModel;
    }

}
