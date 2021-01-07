package com.example.iam.controller.iam;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.diboot.core.controller.BaseCrudRestController;
import com.diboot.core.vo.JsonResult;
import com.diboot.core.vo.Pagination;
import com.diboot.iam.annotation.BindPermission;
import com.diboot.iam.annotation.Log;
import com.diboot.iam.annotation.Operation;
import com.diboot.iam.entity.IamOperationLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 启用devtools，该文件由diboot-devtools自动生成
 */
/**
* 操作日志相关Controller
* @author MyName
* @version 1.0
* @date 2020-11-28
* Copyright © MyCompany
*/
@Slf4j
@Api(tags = {"操作日志"})
@BindPermission(name = "操作日志")
@RestController
@RequestMapping("/iam/operationLog")
public class IamOperationLogController extends BaseCrudRestController<IamOperationLog> {

    /***
    * 查询ViewObject的分页数据
    * <p>
    * url请求参数示例: /list?field=abc&pageSize=20&pageIndex=1&orderBy=id
    * </p>
    * @return
    * @throws Exception
    */
    @ApiOperation(value = "获取列表分页数据")
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
    * 根据资源id查询ViewObject
    * @param id ID
    * @return
    * @throws Exception
    */
    @ApiOperation(value = "根据ID获取详情数据")
    @Log(operation = Operation.LABEL_DETAIL)
    @BindPermission(name = Operation.LABEL_DETAIL, code = Operation.CODE_DETAIL)
    @GetMapping("/{id}")
    public JsonResult getViewObjectMapping(@PathVariable("id") Long id) throws Exception{
        IamOperationLog operationLog = super.getEntity(id);
        return JsonResult.OK(operationLog);
    }
}
