package com.blog.blog_lbz.utils;

/**
 * 返回值枚举类
 */
public enum RespCode {
    SUCCESS(0, "操作成功"),
    WAR(-1, "操作异常，请重试");

    private Integer code;
    private String msg;

    RespCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
