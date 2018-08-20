package com.qwb.takeout.model.dto;

import com.qwb.takeout.model.Items;

import java.util.List;

/**
 *订单DTO(数据传输实体)
 *
 * @author SpringR
 * @param
 * @return
 */
public class OrderDto {

    private String name;

    private String phone;

    private String address;

    private String openid;

    private List<Items> items;

    public OrderDto(String name, String phone, String address, String openid, List<Items> items) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.openid = openid;
        this.items = items;
    }

    public OrderDto() {
    }

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public void setItems(List<Items> items) {
        this.items = items;
    }
}
