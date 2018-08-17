package com.qwb.takeout.enumCode;

public enum OrderEnum {
    VAILD("有效订单",(byte)1),
    INVAILD("无效订单",(byte)0);

    private String msg;
    private Byte code;

    OrderEnum(String msg,Byte code){
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
