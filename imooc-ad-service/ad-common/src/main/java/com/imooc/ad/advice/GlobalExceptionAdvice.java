package com.imooc.ad.advice;

import com.imooc.ad.exception.AdException;
import com.imooc.ad.vo.CommonResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionAdvice {

    /**
     * 对AdException进行统一处理
     * ExceptionHandler: 可以对指定的异常进行拦截
     * 优化: 定义多种类异常,并实现对应的异常处理.
     * 例如: 推广单元的操作出现异常,抛出AdUnitException
     * Binlog解析异常,抛出BinlogException
     * @param req
     * @param ex
     * @return
     */
    @ExceptionHandler(value = AdException.class)
    public CommonResponse<String> handlerAdException(HttpServletRequest req,
                                                     AdException ex){
        // 统一异常接口的响应
        // 优化: 定义不同类型的异常枚举(异常码和异常信息)
        CommonResponse<String> response = new CommonResponse<>(-1,
                "business error");
        response.setData(ex.getMessage());
        return response;
    }

}
