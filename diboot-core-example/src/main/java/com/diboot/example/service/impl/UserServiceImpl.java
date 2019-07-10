package com.diboot.example.service.impl;

import com.diboot.core.service.impl.BaseServiceImpl;
import com.diboot.example.entity.User;
import com.diboot.example.mapper.UserMapper;
import com.diboot.example.service.UserService;
import org.springframework.stereotype.Service;

/**
 * 员工相关Service
 * @author Mazhicheng
 * @version 2018/12/23
 * Copyright © www.dibo.ltd
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements UserService {

}
