package com.example.service.impl;

import com.diboot.core.service.impl.BaseServiceImpl;
import com.example.entity.User;
import com.example.mapper.UserMapper;
import com.example.service.UserService;
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
