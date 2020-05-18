package com.diboot.online.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.web.bind.annotation.*;

import com.diboot.core.util.V;
import com.diboot.core.vo.*;
import com.diboot.core.entity.Dictionary;
import com.diboot.core.controller.BaseCrudRestController;
import com.diboot.iam.annotation.Operation;
import com.diboot.iam.annotation.BindPermission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.validation.Valid;
import java.util.List;

/**
* 数据字典相关Controller
* @author yangzhao
* @version 2.0.0
* @date 2020-03-24
* Copyright © diboot.com
*/
@RestController
@RequestMapping("/dictionary")
@BindPermission(name = "数据字典")
public class DictionaryController extends BaseCrudRestController<Dictionary> {
    private static final Logger log = LoggerFactory.getLogger(DictionaryController.class);

    /***
    * 查询ViewObject的分页数据
    * <p>
    * url请求参数示例: /list?field=abc&pageSize=20&pageIndex=1&orderBy=id
    * </p>
    * @return
    * @throws Exception
    */
    @BindPermission(name = "查看列表", code = Operation.LIST)
    @GetMapping("/list")
    public JsonResult getViewObjectListMapping(Dictionary entity, Pagination pagination) throws Exception{
        QueryWrapper<Dictionary> queryWrapper = super.buildQueryWrapper(entity);
        List<DictionaryVO> voList = dictionaryService.getViewObjectList(queryWrapper, pagination, DictionaryVO.class);
        return JsonResult.OK(voList).bindPagination(pagination);
    }

    /***
    * 根据资源id查询ViewObject
    * @param id ID
    * @return
    * @throws Exception
    */
    @BindPermission(name = "查看详情", code = Operation.DETAIL)
    @GetMapping("/{id}")
    public JsonResult getViewObjectMapping(@PathVariable("id") Long id) throws Exception{
        return super.getViewObject(id, DictionaryVO.class);
    }

    /**
    * 创建资源对象
    * @param entityVO
    * @return JsonResult
    * @throws Exception
    */
    @BindPermission(name = "新建", code = Operation.CREATE)
    @PostMapping("/")
    public JsonResult createEntityMapping(@RequestBody @Valid DictionaryVO entityVO) throws Exception {
        boolean success = dictionaryService.createDictAndChildren(entityVO);
        if(!success){
            return JsonResult.FAIL_OPERATION("保存数据字典失败！");
        }
        return JsonResult.OK();
    }

    /***
    * 根据ID更新资源对象
    * @param entityVO
    * @return JsonResult
    * @throws Exception
    */
    @BindPermission(name = "更新", code = Operation.UPDATE)
    @PutMapping("/{id}")
    public JsonResult updateEntityMapping(@PathVariable("id")Long id, @Valid @RequestBody DictionaryVO entityVO) throws Exception {
        entityVO.setId(id);
        boolean success = dictionaryService.updateDictAndChildren(entityVO);
        if(!success){
            return JsonResult.FAIL_OPERATION("更新数据字典失败！");
        }
        return JsonResult.OK();
    }

    /***
    * 根据id删除资源对象
    * @param id
    * @return
    * @throws Exception
    */
    @BindPermission(name = "删除", code = Operation.DELETE)
    @DeleteMapping("/{id}")
    public JsonResult deleteEntityMapping(@PathVariable("id")Long id) throws Exception {
        boolean success = dictionaryService.deleteDictAndChildren(id);
        if(!success){
            return JsonResult.FAIL_OPERATION("删除数据字典失败！");
        }
        return JsonResult.OK();
    }

    /***
    * 获取数据字典数据列表
    * @param type
    * @return
    * @throws Exception
    */
    @GetMapping("/items/{type}")
    public JsonResult getItems(@PathVariable("type")String type) throws Exception{
        if (V.isEmpty(type)){
            return JsonResult.FAIL_INVALID_PARAM("type参数未指定");
        }
        List<KeyValue> itemsList = dictionaryService.getKeyValueList(type);
        return JsonResult.OK(itemsList);
    }

    /**
    * 校验类型编码是否重复
    * @param id
    * @param type
    * @return
    */
    @GetMapping("/checkTypeDuplicate")
    public JsonResult checkTypeDuplicate(@RequestParam(required = false) Long id, @RequestParam String type) {
        if (V.notEmpty(type)) {
            LambdaQueryWrapper<Dictionary> wrapper = new LambdaQueryWrapper();
            wrapper.select(Dictionary::getId).eq(Dictionary::getType, type).eq(Dictionary::getParentId, 0);
            if (V.notEmpty(id)) {
                wrapper.ne(Dictionary::getId, id);
            }
            boolean alreadyExists = dictionaryService.exists(wrapper);
            if (alreadyExists) {
                return new JsonResult(Status.FAIL_OPERATION, "类型编码已存在");
            }
        }
        return JsonResult.OK();
    }

}