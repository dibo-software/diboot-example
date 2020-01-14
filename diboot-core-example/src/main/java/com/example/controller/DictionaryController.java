package com.example.controller;

import com.diboot.core.util.V;
import com.diboot.core.vo.JsonResult;
import com.diboot.core.vo.KeyValue;
import com.diboot.core.vo.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.diboot.core.entity.Dictionary;
import com.diboot.core.vo.DictionaryVO;
import com.diboot.core.service.DictionaryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

/**
 * 建议启用devtools，该文件将由diboot-devtools自动生成
 */
/**
* 数据字典相关Controller，继承BaseCrudMappingRestController获得CRUD接口
* @author www.dibo.ltd
* @version 2.0
* @date 2019-11-27
* Copyright © dibo.ltd
*/
@RestController
@RequestMapping("/dictionary")
@Slf4j
public class DictionaryController extends BaseCrudMappingRestController<Dictionary, DictionaryVO> {

    @Autowired
    private DictionaryService dictionaryService;

    /***
     * 获取数据字典数据列表，访问测试: http://localhost:8080/example/dictionary/items/GENDER
     * @param type
     * @return
     * @throws Exception
     */
    @GetMapping("/items/{type}")
    public JsonResult getItems(@PathVariable("type")String type) throws Exception{
        if (V.isEmpty(type)){
            return new JsonResult(Status.FAIL_INVALID_PARAM);
        }
        List<KeyValue> itemsList = dictionaryService.getKeyValueList(type);
        return new JsonResult(itemsList);
    }

}