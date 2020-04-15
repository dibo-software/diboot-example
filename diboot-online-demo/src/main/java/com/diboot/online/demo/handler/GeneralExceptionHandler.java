package com.diboot.online.demo.handler;

import com.diboot.core.handler.DefaultExceptionHandler;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
* 通用异常处理类
* @author yangzhao
* @version 2.0.0
* @date 2020-03-24
* Copyright © diboot.com
*/
@ControllerAdvice
public class GeneralExceptionHandler extends DefaultExceptionHandler{
    private static final Logger log = LoggerFactory.getLogger(GeneralExceptionHandler.class);

}