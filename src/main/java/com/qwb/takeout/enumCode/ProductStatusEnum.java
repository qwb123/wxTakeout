package com.qwb.takeout.enumCode;

import sun.print.DialogOwner;

public enum  ProductStatusEnum {

    UP("上架",(byte)1),DOWN("下架",(byte)0);


    private String name;
    private byte code;

    ProductStatusEnum(String name,byte code){
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public byte getCode() {
        return code;
    }
}
