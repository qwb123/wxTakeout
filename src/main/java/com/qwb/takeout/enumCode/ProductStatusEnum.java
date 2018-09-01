package com.qwb.takeout.enumCode;

import sun.print.DialogOwner;

public enum  ProductStatusEnum implements EnumCommon{

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

    @Override
    public Byte getCode() {
        return code;
    }
}
