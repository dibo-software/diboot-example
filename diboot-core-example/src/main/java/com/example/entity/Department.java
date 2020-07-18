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
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 无需手写，启用devtools，该文件将自动生成
 */
/**
 * Department部门
 * @author www.dibo.ltd
 * @version v2.0
 * @date 2018/12/27
 */
@Getter
@Setter
@Accessors(chain = true)
public class Department extends BaseCustomEntity {
    private static final long serialVersionUID = -4849732665419794547L;

    @TableField
    @NotNull(message = "parentId不能为空")
    private Long parentId;

    @TableField
    @NotNull(message = "单位ID不能为空")
    private Long orgId;

    @TableField
    @NotNull(message = "部门名称不能为空")
    @Length(min = 10, max = 20, message = "部门名称长度需>=10且<=20")
    private String name;

    private Date createTime;

}