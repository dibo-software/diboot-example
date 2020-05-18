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

import com.diboot.core.vo.JsonResult;
import com.diboot.core.vo.Pagination;
import com.example.entity.User;
import com.example.vo.UserVO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;

/**
 * User相关常规CRUD Controller
 * @author www.dibo.ltd
 * @version v2.0
 * @date 2019/7/19
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseCustomCrudRestController<User> {

    /***
     * 查询ViewObject的分页数据
     * <p>
     * url请求参数示例: /list?field=abc&pageSize=20&pageIndex=1&orderBy=id
     * </p>
     * @return
     * @throws Exception
     */
    @GetMapping("/list")
    public JsonResult getViewObjectListWithMapping(User entity, Pagination pagination) throws Exception{
        return super.getViewObjectList(entity, pagination, UserVO.class);
    }

    /***
     * 根据资源id查询ViewObject
     * @param id ID
     * @return
     * @throws Exception
     */
    @GetMapping("/{id}")
    public JsonResult getViewObjectWithMapping(@PathVariable("id") Serializable id) throws Exception{
        return super.getViewObject(id, UserVO.class);
    }

    /***
     * 创建资源对象
     * @param entity
     * @return JsonResult
     * @throws Exception
     */
    @PostMapping("/")
    public JsonResult createEntityWithMapping(@Valid @RequestBody User entity) throws Exception {
        return super.createEntity(entity);
    }

    /***
     * 根据ID更新资源对象
     * @param entity
     * @return JsonResult
     * @throws Exception
     */
    @PutMapping("/{id}")
    public JsonResult updateEntityWithMapping(@PathVariable("id")Serializable id, @Valid @RequestBody User entity) throws Exception {
        return super.updateEntity(id, entity);
    }

    /***
     * 根据id删除资源对象
     * @param id
     * @return
     * @throws Exception
     */
    @DeleteMapping("/{id}")
    public JsonResult deleteEntityWithMapping(@PathVariable("id")Serializable id) throws Exception {
        return super.deleteEntity(id);
    }

}
