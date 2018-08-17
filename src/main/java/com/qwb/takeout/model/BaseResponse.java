package com.qwb.takeout.model;

import java.io.Serializable;

public class BaseResponse<T> implements Serializable {

    /**
     * 返回码
     * 0表示错误  1表示成功
     */
    private Integer code;

    /**
     * 返回信息
     */
    private String msg;

    /**
     * 返回数据
     */
    private T data;

    private BaseResponse(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> BaseResponse<T> success(T data){
        return new BaseResponse<T>(1,"SUCCESS",data);
    }

    public static <T> BaseResponse<T> error(T data){
        return new BaseResponse<T>(0,"内部错误",data);
    }


    public static <T> BaseResponse<T> error(T data, String msg){
        return new BaseResponse<T>(0,msg,data);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
