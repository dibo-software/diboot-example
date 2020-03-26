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
package com.example.iam.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 建议启用devtools，该文件由diboot-devtools自动生成
 */
@Getter
@Setter
@Accessors(chain = true)
public class BaseUserInfoDTO implements Serializable {
    private static final long serialVersionUID = -3223502578145175877L;

    @NotNull(message = "真实姓名不能为空")
    @Length(max = 50, message = "真实姓名长度应小于50")
    private String realname;

    @NotNull(message = "性别不能为空")
    @Length(max = 10, message = "性别长度应小于10")
    private String gender;

    @Length(max = 20, message = "手机号长度应小于20")
    private String mobilePhone;

    @Length(max = 50, message = "Email长度应小于50")
    private String email;


}
