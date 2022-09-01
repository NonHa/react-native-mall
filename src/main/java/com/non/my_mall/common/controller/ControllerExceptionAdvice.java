package com.non.my_mall.common.controller;

import com.non.my_mall.common.api.CommonResult;
import com.non.my_mall.utils.APIException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * 这里拦截BindException.class就好了。最后在返回之前，我们对异常信息进行包装一下，包装成ResultVo，
 * 当然要跟上ResultCode.VALIDATE_ERROR的异常状态码。这样前端妹妹看到VALIDATE_ERROR的状态码，就会调用数据校验异常的弹窗提示用户哪里没填好
 * */
@RestControllerAdvice
public class ControllerExceptionAdvice {

    @ExceptionHandler({BindException.class})
    public CommonResult MethodArgumentNotValidExceptionHandler(BindException e) {
        // 从异常对象中拿到ObjectError对象
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        return CommonResult.failed(objectError.getDefaultMessage());
    }

    @ExceptionHandler(APIException.class)
    public CommonResult APIExceptionHandler(APIException e) {
        // log.error(e.getMessage(), e); 由于还没集成日志框架，暂且放着，写上TODO
        return  CommonResult.failed(e.getMessage());
    }
}