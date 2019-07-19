package com.tml.exception;


public class BaseException extends Exception {

    private static final long serialVersionUID = -2553662280126616101L;

    private int errorCode = -1;
    private String msg;


    public BaseException(IExceptionEnums exceptionEnums) {
        this(exceptionEnums.getCode(), exceptionEnums.getMsg());
    }

    public BaseException(int errorCode) {
        super();
        this.errorCode = errorCode;
    }

    public BaseException(int errorCode, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
    }

    public BaseException(int errorCode, String message) {
        super();
        this.errorCode = errorCode;
        this.msg = message;

    }

    public BaseException(int errorCode, String message, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
        this.msg = message;
    }

    public String getMessage() {

        return this.msg;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

}
