package com.example.service.impl;

import com.diboot.core.service.impl.BaseServiceImpl;
import com.example.entity.Organization;
import com.example.mapper.OrganizationMapper;
import com.example.service.OrganizationService;
import org.springframework.stereotype.Service;

/**
 * 单位相关Service实现
 * @author Mazc
 * @version 2018/12/23
 */
@Service
public class OrganizationServiceImpl extends BaseServiceImpl<OrganizationMapper, Organization> implements OrganizationService {

}
