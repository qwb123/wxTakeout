package com.qwb.takeout.model;

import java.io.Serializable;

public class Items implements Serializable {

    private Long productId;

    private int productQuantity;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public Items() {
    }

    public Items(Long productId, int productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }

    @Override
    public String toString() {
        return "Item{" + "productId='" + productId + '\'' + ", productQuantity='" + productQuantity + '\'' + '}';
    }
}
