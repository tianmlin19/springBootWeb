package com.tml.exception;

/**
 * Created by admin on 2019/3/2.
 */
public enum CommonExceptionEnums implements IExceptionEnums {

    INVALID_PARAMETER(100001, "非法参数"),
    INTERNET_CONNECTION_FAIL(100002, "网络连接异常"),;

    private int code;
    private String msg;

    CommonExceptionEnums(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }

}
