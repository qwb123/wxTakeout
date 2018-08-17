package com.qwb.takeout.enumCode;

public enum OrderPayEnum {

    NoPay("未付款",(byte)0),
    HadPay("已经付款",(byte)1);

    private String msg;
    private Byte code;

    OrderPayEnum(String msg,Byte code){
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public Byte getCode() {
        return code;
    }
}
