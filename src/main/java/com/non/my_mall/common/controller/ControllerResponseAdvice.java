package com.non.my_mall.common.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.non.my_mall.common.annotation.NotControllerResponseAdvice;
import com.non.my_mall.common.api.CommonResult;
import com.non.my_mall.utils.APIException;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @RestControllerAdvice(basePackages = {"com.bugpool.leilema"})自动扫描了所有指定包下的controller，在Response时进行统一处理
 * 重写supports方法，也就是说，当返回类型已经是ResultVo了，那就不需要封装了，当不等与ResultVo时才进行调用beforeBodyWrite方法，跟过滤器的效果是一样的
 * 最后重写我们的封装方法beforeBodyWrite，注意除了String的返回值有点特殊，无法直接封装成json，我们需要进行特殊处理，其他的直接new ResultVo(data);就ok了
 * */
@RestControllerAdvice(basePackages = {"com.non.my_mall"})
public class ControllerResponseAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        // response是CommonResult类型，或者注释了NotControllerResponseAdvice都不进行包装
        return !methodParameter.getParameterType().isAssignableFrom(CommonResult.class)
                && !methodParameter.hasMethodAnnotation(NotControllerResponseAdvice.class);
    }

    @Override
    public Object beforeBodyWrite(Object data, MethodParameter returnType, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest request, ServerHttpResponse response)  {

        // String类型不能直接包装
        if (returnType.getGenericParameterType().equals(String.class)) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                // 将数据包装在ResultVo里后转换为json串进行返回
                return objectMapper.writeValueAsString(CommonResult.success(data));
            } catch (JsonProcessingException e) {
                throw new APIException(e.getMessage());
            }
        }
        // 否则直接包装成ResultVo返回
        return CommonResult.success(data);
    }
}