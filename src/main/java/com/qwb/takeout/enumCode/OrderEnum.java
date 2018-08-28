package com.qwb.takeout.enumCode;

public enum OrderEnum implements EnumCommon{
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

    @Override
    public Byte getCode() {
        return code;
    }

    public static OrderEnum getOrderEnum(int code){
        for(OrderEnum orderEnum: OrderEnum.values()){
            if(orderEnum.getCode() == code){
                return orderEnum;
            }
        }
        return null;
    }
}
