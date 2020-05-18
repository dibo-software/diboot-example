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

import com.diboot.core.entity.Dictionary;
import com.diboot.core.vo.JsonResult;
import com.diboot.core.vo.KeyValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class DictionaryController extends BaseCustomCrudRestController<Dictionary> {

    /***
     * 获取数据字典数据列表，访问测试: http://localhost:8080/example/dictionary/items/GENDER
     * @param type
     * @return
     * @throws Exception
     */
    @GetMapping("/items/{type}")
    public JsonResult getItems(@PathVariable("type")String type) throws Exception{
        List<KeyValue> itemsList = dictionaryService.getKeyValueList(type);
        return new JsonResult(itemsList);
    }

}