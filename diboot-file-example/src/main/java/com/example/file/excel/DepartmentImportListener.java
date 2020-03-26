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
package com.example.file.excel;

import com.diboot.core.util.BeanUtils;
import com.diboot.core.util.ContextHelper;
import com.diboot.file.excel.listener.FixedHeadExcelListener;
import com.example.file.entity.Department;
import com.example.file.service.DepartmentService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <Description>
 *
 * @author www.dibo.ltd
 * @version v2.0
 * @date 2020/02/19
 */
@Component
public class DepartmentImportListener extends FixedHeadExcelListener<DepartmentExcelModel> {

    /**
     * 自定义扩展的校验
     * @param dataList
     */
    @Override
    protected void additionalValidate(List<DepartmentExcelModel> dataList, Map<String, Object> requestParams) {
        dataList.stream().forEach(data->{
            // 示例校验单位是否匹配
            //if(!"帝博".equals(data.getOrgName())){
            //    data.addValidateError("单位名称不匹配");
            //}
        });
    }

    /**
     * 自定义保存数据
     * @param dataList
     */
    @Override
    protected void saveData(List<DepartmentExcelModel> dataList, Map<String, Object> paramsMap) {
        // 转换数据
        List<Department> departmentList = new ArrayList<>();
        for(DepartmentExcelModel model : getDataList()){
            // 示例Bean转换
            Department department = BeanUtils.convert(model, Department.class);
            //示例通过名称换ID
            Long orgId = 1001L;
            Long parentId = 10L;
            department.setOrgId(orgId);
            department.setParentId(parentId);

            departmentList.add(department);
            // 字典被转换为存储值
            System.out.println(model.getDict());
        }
        //this.uploadFileUuid 上传文件对应的UUID;
        // 保存数据
        ContextHelper.getBean(DepartmentService.class).createEntities(departmentList);
    }

}
