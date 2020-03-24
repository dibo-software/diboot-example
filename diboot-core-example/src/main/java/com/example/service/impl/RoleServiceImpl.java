package com.example.service.impl;

import com.diboot.core.service.impl.BaseServiceImpl;
import com.example.entity.Role;
import com.example.mapper.RoleMapper;
import com.example.service.RoleService;
import org.springframework.stereotype.Service;

/**
 * 角色相关Service，此处展示继承自Mybatis-plus的IService，绑定注解同样支持
 * @author www.dibo.ltd
 * @version 2018/12/23
 * Copyright © www.dibo.ltd
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleMapper, Role> implements RoleService {

}
