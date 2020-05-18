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
import com.example.entity.User;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

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

    @BindQuery(comparison = Comparison.BETWEEN_BEGIN, field = "create_time")
    private Date createTimeBegin;

    @BindQuery(comparison = Comparison.BETWEEN_END, field = "create_time")
    private Date createTimeEnd;

}
