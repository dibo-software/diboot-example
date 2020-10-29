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
package com.example.dto;

import com.diboot.core.binding.query.BindQuery;
import com.diboot.core.binding.query.Comparison;
import com.diboot.core.util.D;
import com.example.entity.User;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 无需手写，启用devtools，该文件将自动生成
 */
/**
 * DTO测试样例，用于自动绑定QueryWrapper
 * @author www.dibo.ltd
 * @version v2.0
 * @date 2019/08/06
 */
@Getter @Setter @Accessors(chain = true)
public class UserDto extends User{
    private static final long serialVersionUID = 7831455557134155439L;

    @BindQuery(comparison = Comparison.STARTSWITH)
    private String username;

    @BindQuery(comparison = Comparison.IN, field = "gender")
    private String[] genders;

    /**
     * 创建时间-起始
     */
    @BindQuery(comparison = Comparison.GE, field = "createTime")
    private Date createTimeBegin;

    /**
     * 创建时间-截止
     */
    @BindQuery(comparison = Comparison.LT, field = "createTime")
    private Date createTimeEnd;

    public UserDto setCreateTimeEnd(Date createTimeEnd) {
        this.createTimeEnd = D.nextDay(createTimeEnd);
        return this;
    }
}
