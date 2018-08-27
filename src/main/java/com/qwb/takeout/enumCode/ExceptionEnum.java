package com.qwb.takeout.enumCode;

public enum ExceptionEnum {

    PRODUCT_NOT_EXIST("商品不存在",10),
    PRODUCT_STOCK_LACK("商品库存不足",11),
    PRODUCT_PAY_FAIL("支付失败",12),
    ORDER_CANCEL_FAIL("订单取消失败",13),
    ORDER_ISNOT_EXIST("订单不存在",14),
    OPENID_IS_NOT("用户不存在",15);

    private String msg;
    private int code;

    ExceptionEnum(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCocde(int cocde) {
        this.code = cocde;
    }
}
