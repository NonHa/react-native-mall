package com.non.my_mall.utils;

import com.alibaba.druid.wall.violation.ErrorCode;
import com.non.my_mall.common.api.AppCode;
import com.non.my_mall.common.api.IErrorCode;
import lombok.Getter;

@Getter
public class APIException extends RuntimeException {
    private long code;
    private String msg;

    // 手动设置异常
    public APIException(IErrorCode statusCode, String message) {
        // message用于用户设置抛出错误详情，例如：当前价格-5，小于0
        super(message);
        // 状态码
        this.code = statusCode.getCode();
        // 状态码配套的msg
        this.msg = statusCode.getMessage();
    }

    // 默认异常使用APP_ERROR状态码
    public APIException(String message) {
        super(message);
        this.code = AppCode.APP_ERROR.getCode();
        this.msg = AppCode.APP_ERROR.getMessage();
    }

}