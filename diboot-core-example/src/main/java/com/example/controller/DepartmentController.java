/*
 * Copyright (c) 2015-2020, www.dibo.ltd (service@dibo.ltd).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * https://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.diboot.core.binding.Binder;
import com.diboot.core.exception.BusinessException;
import com.diboot.core.util.V;
import com.diboot.core.vo.JsonResult;
import com.diboot.core.vo.KeyValue;
import com.diboot.core.vo.Pagination;
import com.diboot.core.vo.Status;
import com.example.dto.DepartmentDTO;
import com.example.entity.Department;
import com.example.service.DepartmentService;
import com.example.vo.DepartmentVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 无需手写，启用devtools，该文件将自动生成
 */
/**
 * Department相关Controller示例: 继承自BaseCrudRestController，自定义接口
 * @author www.dibo.ltd
 * @version v2.0
 * @date 2019/1/19
 */
@RestController
@RequestMapping("/department")
@Slf4j
public class DepartmentController extends BaseCustomCrudRestController<Department> {

    @Autowired
    private DepartmentService departmentService;

    /***
     * 查询ViewObject的分页数据
     * <p>
     * url参数示例: /list?pageSize=20&pageIndex=1&orderBy=id&name=xx&orgName=yyy
     * 如果查询条件包含关联表字段，则自动构建关联查询
     * </p>
     * @return
     * @throws Exception
     */
    @GetMapping("/listWithDTO")
    public JsonResult getVOList(DepartmentDTO departmentDto, Pagination pagination) throws Exception{
        // DTO转换为QueryWrapper，若无@BindQuery注解默认映射为等于=条件，有注解映射为注解条件。
        // 如果查询条件包含关联表字段，则自动构建关联查询，否则构建为单表查询
        QueryWrapper<Department> queryWrapper = super.buildQueryWrapper(departmentDto);
        // 查询当前页的Entity主表数据
        List<Department> entityList = Binder.joinQueryList(queryWrapper, Department.class, pagination);
        // 转换VO中注解绑定的关联
        List<DepartmentVO> voList = Binder.convertAndBindRelations(entityList, DepartmentVO.class);
        // 返回结果
        return JsonResult.OK(voList).bindPagination(pagination);
    }

    /**
     * ID-Name的映射键值对，用于前端Select控件筛选等
     * @return
     */
    @GetMapping("/kv")
    public JsonResult getKVPairList(){
        //测试异常处理，前端指定application/json，返回异常包装JSON
        if(V.notEmpty(getString("error"))){
            throw new BusinessException(Status.FAIL_INVALID_PARAM, "请求参数不匹配: xxx");
        }
        Wrapper wrapper = new QueryWrapper<Department>().lambda()
            .select(Department::getName, Department::getId, Department::getCreateTime);
        List<KeyValue> list = departmentService.getKeyValueList(wrapper);
        return JsonResult.OK(list);
    }

}