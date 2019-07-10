package com.diboot.example.service.impl;

import com.diboot.core.service.impl.BaseServiceImpl;
import com.diboot.example.entity.Organization;
import com.diboot.example.mapper.OrganizationMapper;
import com.diboot.example.service.OrganizationService;
import org.springframework.stereotype.Service;

/**
 * 单位相关Service实现
 * @author Mazhicheng
 * @version 2018/12/23
 */
@Service
public class OrganizationServiceImpl extends BaseServiceImpl<OrganizationMapper, Organization> implements OrganizationService {

}
