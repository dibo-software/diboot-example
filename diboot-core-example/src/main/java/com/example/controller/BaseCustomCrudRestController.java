package com.example.controller;

import com.diboot.core.controller.BaseCrudRestController;
import com.diboot.core.entity.BaseEntity;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * 启用devtools，该文件将由diboot-devtools自动生成
 */
/**
* 通用CRUD通用父类RestController，子类继承即可拥有CRUD接口（禁止只读接口Controller继承）
* @author www.dibo.ltd
* @version 2.0
* @date 2019-11-27
* Copyright © dibo.ltd
*/
@Slf4j
public class BaseCustomCrudRestController<E extends BaseEntity, VO extends Serializable> extends BaseCrudRestController {

}