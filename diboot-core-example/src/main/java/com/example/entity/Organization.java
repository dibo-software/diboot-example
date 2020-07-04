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
package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Getter;
import lombok.Setter;

/**
 * 无需手写，启用devtools，该文件将自动生成
 */
/**
 * 单位Entity
 * @author www.dibo.ltd
 * @version v2.0
 * @date 2019/1/5
 */
@Getter
@Setter
public class Organization extends BaseCustomEntity {
    private static final long serialVersionUID = -5889309041570465909L;

    @TableField
    private Long parentId;

    @TableField
    private String name;

    @TableField
    private String telphone;

}
