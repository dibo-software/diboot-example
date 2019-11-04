package com.example.service.impl;

import com.diboot.core.service.impl.BaseServiceImpl;
import com.example.entity.Department;
import com.example.mapper.DepartmentMapper;
import com.example.service.DepartmentService;
import org.springframework.stereotype.Service;

/**
 * 部门相关Service实现
 * @author Mazc
 * @version v2.0
 * @date 2019/1/30
 */
@Service
public class DepartmentServiceImpl extends BaseServiceImpl<DepartmentMapper, Department> implements DepartmentService {

}
