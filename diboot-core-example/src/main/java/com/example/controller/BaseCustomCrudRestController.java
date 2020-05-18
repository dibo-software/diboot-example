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
import lombok.extern.slf4j.Slf4j;

/**
 * 启用devtools，该文件将由diboot-devtools自动生成
 */
/**
* 通用CRUD通用父类RestController，子类继承即可拥有CRUD接口（禁止只读接口Controller继承）
* @author www.dibo.ltd
* @version 2.0
* @date 2019-11-27
* Copyright © dibo.ltd
*/
@Slf4j
public class BaseCustomCrudRestController<E extends BaseEntity> extends BaseCrudRestController {

}