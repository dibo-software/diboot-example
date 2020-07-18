package com.diboot.online.demo.service.impl;

import com.diboot.core.mapper.BaseCrudMapper;
import com.diboot.core.service.impl.BaseServiceImpl;
import com.diboot.online.demo.service.BaseCustomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* 自定义BaseService接口实现
* @author yangzhao
* @version 2.0.0
* @date 2020-03-24
 * Copyright © diboot.com
*/
public class BaseCustomServiceImpl<M extends BaseCrudMapper<T>, T> extends BaseServiceImpl<M, T> implements BaseCustomService<T> {
    private static final Logger log = LoggerFactory.getLogger(BaseCustomServiceImpl.class);

}
