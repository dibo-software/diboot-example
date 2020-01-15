package com.example.iam.controller;

import com.diboot.core.controller.BaseCrudRestController;
import com.diboot.core.entity.BaseEntity;
import com.diboot.core.vo.JsonResult;
import com.diboot.core.vo.Pagination;
import com.diboot.iam.annotation.BindPermission;
import com.diboot.iam.annotation.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.Serializable;

/**
 * 建议启用devtools，该文件将由diboot-devtools自动生成
 */
/**
* 通用CRUD通用父类RestController，子类继承即可拥有CRUD接口（只读接口Controller禁止继承）
* @author www.dibo.ltd
* @version 2.0
* @date 2019-12-03
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
    @BindPermission(name = "查看列表", code = Operation.LIST)
    public JsonResult getViewObjectListWithMapping(E entity, Pagination pagination, HttpServletRequest request) throws Exception{
        return getViewObjectList(entity, pagination, request);
    }

    /***
    * 根据资源id查询ViewObject
    * @param id ID
    * @return
    * @throws Exception
    */
    @GetMapping("/{id}")
    @BindPermission(name = "查看详情", code = Operation.DETAIL)
    public JsonResult getViewObjectWithMapping(@PathVariable("id")Serializable id, HttpServletRequest request) throws Exception{
        return getViewObject(id, request);
    }

    /***
    * 创建资源对象
    * @param entity
    * @return JsonResult
    * @throws Exception
    */
    @PostMapping("/")
    @BindPermission(name = "新建", code = Operation.CREATE)
    public JsonResult createEntityWithMapping(@RequestBody @Valid E entity, HttpServletRequest request) throws Exception {
        return createEntity(entity, request);
    }

    /***
    * 根据ID更新资源对象
    * @param entity
    * @return JsonResult
    * @throws Exception
    */
    @PutMapping("/{id}")
    @BindPermission(name = "更新", code = Operation.UPDATE)
    public JsonResult updateEntityWithMapping(@PathVariable("id")Serializable id, @Valid @RequestBody E entity, HttpServletRequest request) throws Exception {
        return updateEntity(id, entity, request);
    }

    /***
    * 根据id删除资源对象
    * @param id
    * @return
    * @throws Exception
    */
    @DeleteMapping("/{id}")
    @BindPermission(name = "删除", code = Operation.DELETE)
    public JsonResult deleteEntityWithMapping(@PathVariable("id")Serializable id, HttpServletRequest request) throws Exception {
        return deleteEntity(id, request);
    }

}