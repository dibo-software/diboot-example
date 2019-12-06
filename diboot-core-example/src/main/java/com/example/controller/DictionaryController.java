package com.example.controller;

import com.diboot.core.util.V;
import com.diboot.core.vo.JsonResult;
import com.diboot.core.vo.KeyValue;
import com.diboot.core.vo.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.diboot.core.entity.Dictionary;
import com.diboot.core.vo.DictionaryVO;
import com.diboot.core.service.DictionaryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

/**
* 数据字典相关Controller
* @author Mazc
* @version 2.0
* @date 2019-11-27
* Copyright © dibo.ltd
*/
@RestController
@RequestMapping("/dictionary")
public class DictionaryController extends BaseCrudMappingRestController<Dictionary, DictionaryVO> {
    private static final Logger log = LoggerFactory.getLogger(DictionaryController.class);

    @Autowired
    private DictionaryService dictionaryService;

    /***
     * 获取数据字典数据列表
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
        return new JsonResult(Status.OK, itemsList);
    }

}