package com.non.my_mall.common.api;

import lombok.Getter;

@Getter
public enum  AppCode implements IErrorCode {

    APP_ERROR(2000, "业务异常"),
    PRICE_ERROR(2001, "价格异常");

    private long code;
    private String message;

    AppCode(int code, String msg) {
        this.code = code;
        this.message = msg;
    }
}
