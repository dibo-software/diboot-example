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
package com.example.iam.controller.iam;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.diboot.core.controller.BaseCrudRestController;
import com.diboot.core.vo.JsonResult;
import com.diboot.core.vo.Pagination;
import com.diboot.iam.annotation.BindPermission;
import com.diboot.iam.annotation.Log;
import com.diboot.iam.annotation.Operation;
import com.diboot.iam.entity.IamOperationLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

/**
 * 建议启用devtools，该文件由diboot-devtools自动生成
 */
/**
* 操作日志
* @author www.dibo.ltd
* @version 1.0.1
* @date 2020-09-18
* Copyright © dibo.ltd
*/
@RestController
@RequestMapping("/iam/operationLog")
@Slf4j
@BindPermission(name = "操作日志")
public class IamOperationLogController extends BaseCrudRestController<IamOperationLog> {

    /***
    * 查询分页数据
    * @return
    * @throws Exception
    */
    @Log(operation = Operation.LABEL_LIST)
    @BindPermission(name = Operation.LABEL_LIST, code = Operation.CODE_LIST)
    @GetMapping("/list")
    public JsonResult getViewObjectListMapping(IamOperationLog entity, Pagination pagination) throws Exception{
        QueryWrapper<IamOperationLog> queryWrapper = super.buildQueryWrapper(entity);
        Integer status = getInteger("status");
        if(status != null){
            if(status.intValue() == 0){
                queryWrapper.eq("status_code", 0);
            }
            else{
                queryWrapper.gt("status_code", 0);
            }
        }
        return super.getEntityListWithPaging(queryWrapper, pagination);
    }

    /***
     * 根据资源id查询详情
     * @param id ID
     * @return
     * @throws Exception
     */
    @Log(operation = Operation.LABEL_DETAIL)
    @BindPermission(name = Operation.LABEL_DETAIL, code = Operation.CODE_DETAIL)
    @GetMapping("/{id}")
    public JsonResult getViewObjectMapping(@PathVariable("id") Serializable id) throws Exception{
        IamOperationLog operationLog = super.getEntity(id);
        return JsonResult.OK(operationLog);
    }

}