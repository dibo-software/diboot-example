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
import com.diboot.core.binding.QueryBuilder;
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
import com.example.vo.OldDepartmentVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
     * url参数示例: /listWithDTO?pageSize=20&pageIndex=1&orderBy=id&name=xx&orgName=yyy
     * 如果查询条件包含关联表字段，则自动构建关联查询
     * </p>
     * @return
     * @throws Exception
     */
    @GetMapping("/listWithDTO")
    public JsonResult getVOList(DepartmentDTO departmentDto, Pagination pagination) throws Exception{
        // DTO转换为QueryWrapper，若无@BindQuery注解默认映射为等于=条件，有注解映射为注解条件。

        // 步骤一：构建 （如果查询条件包含关联表字段，则自动构建关联查询，否则构建为单表查询）
        // 方式1：调用super构建，默认【只取请求参数中的字段】进行构建
        QueryWrapper<Department> queryWrapper = super.buildQueryWrapper(departmentDto);
        // 方式2：调用QueryBuilder构建，取对象中的【全部非空字段】进行构建
        //QueryWrapper<Department> queryWrapper = QueryBuilder.toQueryWrapper(departmentDto);

        // 步骤二：查询（执行queryWrapper查询，得到entity/entityList）
        // 方式1：通过Binder.joinQueryList 查询当前页的Entity主表数据
        //List<Department> entityList = Binder.joinQueryList(queryWrapper, Department.class, pagination);
        // 方式2：通过BaseService.getEntityList 进行查询
        List<Department> entityList = departmentService.getEntityList(queryWrapper, pagination);

        // 转换VO中注解绑定的关联
        List<DepartmentVO> voList = Binder.convertAndBindRelations(entityList, DepartmentVO.class);
        // 返回结果
        return JsonResult.OK(voList).bindPagination(pagination);
    }

    /**
     * 传统Mybatis方式实现关联对象绑定
     * URL访问示例：http://localhost:8080/example/department/getVOByMapperXml?id=10001
     * @param id
     * @return
     * @throws Exception
     */
    @GetMapping("/getVOByMapperXml")
    public JsonResult getVOByMapperXml(@RequestParam("id")Long id) throws Exception{
        OldDepartmentVO departmentVO = departmentService.getDepartmentVOByMybatisXML(id);
        // 返回结果
        return JsonResult.OK(departmentVO);
    }

    /**
     * Diboot注解方式实现关联对象绑定
     * URL访问示例：http://localhost:8080/example/department/getVOByBindAnno?id=10001
     * @param id
     * @return
     * @throws Exception
     */
    @GetMapping("/getVOByBindAnno")
    public JsonResult getVOByBindAnno(@RequestParam("id")Long id) throws Exception{
        DepartmentVO departmentVO = departmentService.getViewObject(id, DepartmentVO.class);
        // 返回结果
        return JsonResult.OK(departmentVO);
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