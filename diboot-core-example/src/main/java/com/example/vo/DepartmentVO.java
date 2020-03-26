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
package com.example.vo;

import com.diboot.core.binding.annotation.BindEntity;
import com.diboot.core.binding.annotation.BindEntityList;
import com.diboot.core.binding.annotation.BindField;
import com.example.entity.Department;
import com.example.entity.Organization;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author www.dibo.ltd
 * @version v2.0
 * @date 2019/1/5
 */
@Getter @Setter @Accessors(chain = true)
public class DepartmentVO extends Department {
    private static final long serialVersionUID = -362116388664907913L;

    // 直接关联Entity中的某字段
    @BindField(entity = Organization.class, field = "name", condition = "this.org_id=id AND name='百度'")
    private String orgName;

    // 直接关联Entity
    @BindEntity(entity = Organization.class, condition="this.org_id=id")
    private Organization organization;

    // 直接关联多个Entity
    @BindEntityList(entity = Department.class, condition = "this.id=parent_id")
    private List<Department> children;

}