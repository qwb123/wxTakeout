package com.qwb.takeout.enumCode;

public enum ExceptionEnum {

    PRODUCT_NOT_EXIST("商品不存在",10),
    PRODUCT_STOCK_LACK("商品库存不足",11),
    PRODUCT_PAY_FAIL("支付失败",12),
    ORDER_CANCEL_FAIL("订单取消失败",13);

    private String msg;
    private int cocde;

    ExceptionEnum(String msg, int cocde) {
        this.msg = msg;
        this.cocde = cocde;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCocde() {
        return cocde;
    }

    public void setCocde(int cocde) {
        this.cocde = cocde;
    }
}
