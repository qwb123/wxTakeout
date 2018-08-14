package com.qwb.takeout.model.vo;

import com.qwb.takeout.model.entity.ProductInfo;

import java.util.ArrayList;
import java.util.List;

public class BuyerProductListVo {

    /**
     * 类型名称
     */
    private String name;

    /**
     * 类型编号
     */
    private int type;

    /**
     *该类型的食品
     */
    private List<ProductInfo> foods;

    public BuyerProductListVo() {
        this.foods = new ArrayList();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<ProductInfo> getFoods() {
        return foods;
    }

    public void setFoods(List<ProductInfo> foods) {
        this.foods = foods;
    }
}
