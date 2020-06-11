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
package com.example.file.controller;

import com.diboot.core.vo.JsonResult;
import com.diboot.file.controller.BaseExcelFileController;
import com.diboot.file.excel.listener.FixedHeadExcelListener;
import com.diboot.file.util.ExcelHelper;
import com.diboot.file.util.FileHelper;
import com.diboot.file.util.HttpHelper;
import com.example.file.entity.Department;
import com.example.file.excel.DepartmentExcelModel;
import com.example.file.excel.DepartmentImportListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Department相关Controller示例: 继承自BaseCrudRestController，自定义接口
 * @author www.dibo.ltd
 * @version v2.0
 * @date 2019/1/19
 */
@RestController
@RequestMapping("/excel")
@Slf4j
public class ExcelController extends BaseExcelFileController {

    @Override
    protected FixedHeadExcelListener getExcelDataListener() {
        return new DepartmentImportListener();
    }

    /**
     * 预览excel数据
     */
    @PostMapping("/preview")
    public JsonResult preview(@RequestParam("file") MultipartFile file) throws Exception {
        return super.excelPreview(file);
    }

    /**
     * 预览无校验错误后 提交
     */
    @PostMapping("/previewSave")
    public <T> JsonResult previewSave(@RequestParam("previewFileName")String previewFileName, @RequestParam("originFileName")String originFileName) throws Exception {
        return super.excelPreviewSave(Department.class, previewFileName, originFileName);
    }

    /**
     * 无预览直接导入
     */
    @PostMapping("/import")
    public <T> JsonResult upload(@RequestParam("file")MultipartFile file) throws Exception {
        return super.uploadExcelFile(file, Department.class);
    }

    /**
     * 导出Excel
     **/
    @GetMapping("/export")
    public JsonResult download(HttpServletResponse response) throws Exception {
        String excelPath = FileHelper.getSystemTempDir() + "temp.xlsx";
        // 构建结果
        List<DepartmentExcelModel> dataList = buildData();
        // 写入excel
        ExcelHelper.writeData(excelPath, "部门列表", dataList);
        // 导出
        HttpHelper.downloadLocalFile(excelPath, "导出Excel示例.xlsx", response);
        return null;
    }

    /**
     * 示例组装数据
     * @return
     */
    private List<DepartmentExcelModel> buildData(){
        List<DepartmentExcelModel> dataList = new ArrayList<>();
        DepartmentExcelModel model = new DepartmentExcelModel();
        model.setOrgName("帝博");
        model.setParentName("研发中心");
        model.setName("产品部");
        model.setDict("M"); //mock字典 导出时转换为显示值
        dataList.add(model);
        return dataList;
    }
}
