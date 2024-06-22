package com.ls.sysuser.pojo;

import lombok.Data;

import java.util.List;

/**
 * 所有接口返回的统一数据接口
 * code
 * msg
 * data
 */


public class R <T>{
    private  Integer code;

    private String msg;

    private List<T> data;

    private Object extra;

    public Object getExtra() {
        return extra;
    }

    public R setExtra(Object extra) {
        this.extra = extra;
        return this;
    }

    public static R success() {
        R responseVo = new R();
        responseVo.setCode(1);
        responseVo.setMsg("success");
        return responseVo;
    }

    public static R error() {
        R responseVo = new R();
        responseVo.setCode(0);
        responseVo.setMsg("error");
        return responseVo;
    }

    public Integer getCode() {
        return code;
    }

    public R<T> setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public R<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public List<T> getData() {
        return data;
    }

    public R<T> setData(List<T> data) {
        this.data = data;
        return this;
    }




}
