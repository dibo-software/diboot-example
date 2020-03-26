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

import com.diboot.core.controller.BaseCrudRestController;
import com.diboot.core.entity.BaseEntity;
import com.diboot.core.vo.JsonResult;
import com.diboot.core.vo.Pagination;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.Serializable;

/**
 * 建议启用devtools，该文件将由diboot-devtools自动生成
 */
/**
* 通用CRUD通用父类RestController，子类继承即可拥有CRUD接口（禁止只读接口Controller继承）
* @author www.dibo.ltd
* @version 2.0
* @date 2019-11-27
* Copyright © dibo.ltd
*/
@Slf4j
public class BaseCrudMappingRestController<E extends BaseEntity, VO extends Serializable> extends BaseCrudRestController {

    /***
    * 查询ViewObject的分页数据
    * <p>
    * url请求参数示例: /list?field=abc&pageSize=20&pageIndex=1&orderBy=id
    * </p>
    * @return
    * @throws Exception
    */
    @GetMapping("/list")
    public JsonResult getViewObjectListWithMapping(E entity, Pagination pagination, HttpServletRequest request) throws Exception{
        return super.getViewObjectList(entity, pagination, request);
    }

    /***
    * 根据资源id查询ViewObject
    * @param id ID
    * @return
    * @throws Exception
    */
    @GetMapping("/{id}")
    public JsonResult getViewObjectWithMapping(@PathVariable("id")Serializable id, HttpServletRequest request) throws Exception{
        return super.getViewObject(id, request);
    }

    /***
    * 创建资源对象
    * @param entity
    * @return JsonResult
    * @throws Exception
    */
    @PostMapping("/")
    public JsonResult createEntityWithMapping(@Valid @RequestBody E entity, HttpServletRequest request) throws Exception {
        return super.createEntity(entity, request);
    }

    /***
    * 根据ID更新资源对象
    * @param entity
    * @return JsonResult
    * @throws Exception
    */
    @PutMapping("/{id}")
    public JsonResult updateEntityWithMapping(@PathVariable("id")Serializable id, @Valid @RequestBody E entity, HttpServletRequest request) throws Exception {
        return super.updateEntity(id, entity, request);
    }

    /***
    * 根据id删除资源对象
    * @param id
    * @return
    * @throws Exception
    */
    @DeleteMapping("/{id}")
    public JsonResult deleteEntityWithMapping(@PathVariable("id")Serializable id, HttpServletRequest request) throws Exception {
        return super.deleteEntity(id, request);
    }

}