package com.example.iam.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.diboot.core.controller.BaseCrudRestController;
import com.diboot.core.entity.Dictionary;
import com.diboot.core.service.DictionaryService;
import com.diboot.core.util.V;
import com.diboot.core.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * 建议启用devtools，该文件由diboot-devtools自动生成
 */
/**
* 数据字典相关Controller
* @author mazc@dibo.ltd
* @version 1.0.1
* @date 2020-03-18
* Copyright © dibo.ltd
*/
@RestController
@RequestMapping("/dictionary")
@Slf4j
public class DictionaryController extends BaseCrudRestController<Dictionary, DictionaryVO> {
    @Autowired
    private DictionaryService dictionaryService;

    /***
    * 查询ViewObject的分页数据
    * <p>
    * url请求参数示例: /list?field=abc&pageSize=20&pageIndex=1&orderBy=id
    * </p>
    * @return
    * @throws Exception
    */
    @GetMapping("/list")
    public JsonResult getViewObjectListMapping(Dictionary entity, Pagination pagination, HttpServletRequest request) throws Exception{
        QueryWrapper<Dictionary> queryWrapper = super.buildQueryWrapper(entity, request);
        List<DictionaryVO> voList = dictionaryService.getViewObjectList(queryWrapper, pagination, DictionaryVO.class);
        return JsonResult.OK(voList).bindPagination(pagination);
    }

    /***
    * 根据资源id查询ViewObject
    * @param id ID
    * @return
    * @throws Exception
    */
    @GetMapping("/{id}")
    public JsonResult getViewObjectMapping(@PathVariable("id") Long id, HttpServletRequest request) throws Exception{
        return super.getViewObject(id, request);
    }

    /**
    * 创建资源对象
    * @param entityVO
    * @return JsonResult
    * @throws Exception
    */
    @PostMapping("/")
    public JsonResult createEntityMapping(@RequestBody @Valid DictionaryVO entityVO, HttpServletRequest request) throws Exception {
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
    @PutMapping("/{id}")
    public JsonResult updateEntityMapping(@PathVariable("id")Long id, @Valid @RequestBody DictionaryVO entityVO, HttpServletRequest request) throws Exception {
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
    @DeleteMapping("/{id}")
    public JsonResult deleteEntityMapping(@PathVariable("id")Long id, HttpServletRequest request) throws Exception {
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
    * @param request
    * @return
    */
    @GetMapping("/checkTypeDuplicate")
    public JsonResult checkTypeDuplicate(@RequestParam(required = false) Long id, @RequestParam String type, HttpServletRequest request) {
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