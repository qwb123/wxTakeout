package com.qwb.takeout.exception;

import com.qwb.takeout.enumCode.ExceptionEnum;

public class SellException extends RuntimeException {

    private int code;

    public SellException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMsg());
        this.code = exceptionEnum.getCocde();
    }
}
