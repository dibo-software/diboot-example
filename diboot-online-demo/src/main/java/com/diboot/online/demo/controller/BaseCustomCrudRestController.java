package com.diboot.online.demo.controller;

import com.diboot.core.controller.BaseCrudRestController;
import com.diboot.core.entity.BaseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.Serializable;

/**
* 自定义通用CRUD父类RestController
* @author yangzhao
* @version 2.0.0
* @date 2020-03-24
* Copyright © diboot.com
*/
public class BaseCustomCrudRestController<E extends BaseEntity> extends BaseCrudRestController {
    private static final Logger log = LoggerFactory.getLogger(BaseCustomCrudRestController.class);

}