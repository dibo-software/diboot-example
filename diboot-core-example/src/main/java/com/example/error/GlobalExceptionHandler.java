package com.example.error;

import com.diboot.core.handle.DefaultExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理类，直接继承diboot-core里的默认实现
 * @author Mazhicheng
 * @version v2.0
 * @date 2019/07/20
 */
@ControllerAdvice
public class GlobalExceptionHandler extends DefaultExceptionHandler{
    private final static Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Override
    protected String getViewName(HttpServletRequest request, Exception ex){
        return "/error";
    }

}