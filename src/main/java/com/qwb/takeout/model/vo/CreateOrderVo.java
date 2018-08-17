package com.qwb.takeout.model.vo;

import com.qwb.takeout.model.Items;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CreateOrderVo implements Serializable {

    private String name;

    private String phone;

    private String address;

    private String openid;

    private List<Items> items = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public List<Items> getItems() {
        return items;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setItem(List<Items> items) {
        this.items = items;
    }

    public CreateOrderVo(String name, String phone, String openid, List<Items> items) {
        this.name = name;
        this.phone = phone;
        this.openid = openid;
        this.items = items;
    }


    public CreateOrderVo() {
    }

    @Override
    public String toString() {
        return "CreateOrderVo{" + "name='" + name + '\'' + ", phone='" + phone + '\'' + ", openid='" + openid + '\'' + ", items=" + items + '}';
    }
}
