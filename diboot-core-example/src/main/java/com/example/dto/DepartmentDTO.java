package com.example.dto;

import com.diboot.core.binding.query.BindQuery;
import com.diboot.core.binding.query.Comparison;
import com.example.entity.Department;
import com.example.entity.Organization;
import lombok.Getter;
import lombok.Setter;

/**
 * 无需手写，启用devtools，该文件将自动生成
 */
/**
 * 部门查询DTO（动态按需自动构建Join查询）
 * @author www.dibo.ltd
 * @version v2.0
 * @date 2020/04/29
 */
@Getter @Setter
public class DepartmentDTO extends Department {
    private static final long serialVersionUID = -6318409902332253627L;

    // 绑定join查询
    @BindQuery(comparison = Comparison.CONTAINS, entity = Organization.class, field = "name", condition = "this.org_id=id")
    private String orgName;

    // 绑定join查询
    @BindQuery(entity = Department.class, field = "name", condition = "this.parent_id=id")
    private String parentName;

}
