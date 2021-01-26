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
package mapper;

import com.diboot.core.binding.annotation.BindEntity;
import com.diboot.core.binding.annotation.BindEntityList;
import com.diboot.core.binding.annotation.BindField;
import com.example.entity.Department;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 无需手写，启用devtools，该文件将自动生成
 */

/**
 * @author www.dibo.ltd
 * @version v2.0
 * @date 2019/1/5
 */
@Data
public class EmployeeVO implements Serializable {
    private static final long serialVersionUID = -362116388664907913L;

    private Long id;

    private String name;

    private Date birthdate;

    // 关联部门名称
    @BindField(entity = Department.class, field = "name", condition = "this.department_id=id")
    private String departmentName;

    // 关联员工档案
    @BindEntity(entity = EmployeeProfile.class, condition = "this.id=employee_id")
    private EmployeeProfile employeeProfile;

    // 关联 员工履历
    @BindEntityList(entity = WorkExperience.class, condition = "this.id=employee_id")
    private List<WorkExperience> workExperienceList;

}