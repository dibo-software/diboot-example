package com.example.file.service.impl;

import com.diboot.core.service.impl.BaseServiceImpl;
import com.example.file.entity.Department;
import com.example.file.mapper.DepartmentMapper;
import com.example.file.service.DepartmentService;
import org.springframework.stereotype.Service;

/**
 * 部门相关Service实现
 * @author www.dibo.ltd
 * @version v2.0
 * @date 2019/1/30
 */
@Service
public class DepartmentServiceImpl extends BaseServiceImpl<DepartmentMapper, Department> implements DepartmentService {

}
