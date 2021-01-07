package com.example.iam.controller.iam;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.diboot.core.util.BeanUtils;
import com.diboot.core.util.D;
import com.diboot.core.util.V;
import com.diboot.core.vo.JsonResult;
import com.diboot.core.vo.Pagination;
import com.diboot.core.vo.Status;
import com.diboot.file.controller.BaseExcelFileController;
import com.diboot.file.entity.UploadFile;
import com.diboot.file.excel.listener.FixedHeadExcelListener;
import com.diboot.file.service.UploadFileService;
import com.diboot.file.util.ExcelHelper;
import com.diboot.iam.annotation.BindPermission;
import com.diboot.iam.annotation.Log;
import com.diboot.iam.entity.IamUser;
import com.diboot.iam.service.IamUserService;
import com.diboot.iam.vo.IamUserVO;
import com.example.iam.excel.listener.IamUserImportExcelListener;
import com.example.iam.excel.model.IamUserExportModel;
import com.example.iam.excel.model.IamUserImportModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 启用devtools，该文件由diboot-devtools自动生成
 */
/**
 * 用户Excel上传相关Controller
 * @author MyName
 * @version 1.0
 * @date 2020-11-28
 * Copyright © MyCompany
 */
@RequestMapping("/iam/user/excel")
@RestController
public class IamUserExcelController extends BaseExcelFileController {

    @Autowired
    private UploadFileService uploadFileService;

    @Autowired
    private IamUserService iamUserService;

    /**
     * 获取文件上传记录（分页）
     *
     * @param pagination
     * @return
     * @throws Exception
     */
    @GetMapping("/list")
    public JsonResult list(Pagination pagination) throws Exception {
        List<UploadFile> uploadFileList = uploadFileService.getEntityList(null, pagination);
        return JsonResult.OK(uploadFileList).bindPagination(pagination);
    }

    /**
     * 下载示例文件
     * @param response
     * @throws Exception
     */
    @BindPermission(name = "导入用户示例文件下载")
    @GetMapping("/downloadExample")
    public void downloadExample(HttpServletResponse response) throws Exception{
        String fileName = "用户导入示例.xlsx";
        ExcelHelper.exportExcel(response, fileName, IamUserImportModel.class, null);
    }

    /***
     * 预览数据
     * @throws Exception
     */
    @PostMapping("/preview")
    public JsonResult preview(@RequestParam("file") MultipartFile file) throws Exception {
        return super.excelPreview(file);
    }

    @PostMapping("/previewSave")
    public JsonResult previewSave(@RequestParam(BaseExcelFileController.PREVIEW_FILE_NAME) String previewFileName,
                                  @RequestParam(BaseExcelFileController.ORIGIN_FILE_NAME) String originFileName,
                                  HttpServletRequest request) throws Exception {
        return super.excelPreviewSave(IamUser.class, previewFileName, originFileName);
    }

    /***
     * 上传excel并保存
     * @throws Exception
     */
    @Log(operation = "导入用户Excel", businessObj = "IamUser")
    @PostMapping("/upload")
    public JsonResult upload(@RequestParam("file") MultipartFile file) throws Exception {
        return super.uploadExcelFile(file, IamUser.class);
    }

    /***
     * 人员列表导出
     * @param response
     * @return
     * @throws Exception
     */
    @Log(operation = "导出用户", businessObj = "IamUser")
    @GetMapping("/export")
    public JsonResult export(IamUser iamUser, HttpServletResponse response) throws Exception {
        QueryWrapper<IamUser> queryWrapper = super.buildQueryWrapper(iamUser);
        List<IamUserVO> iamUserList = iamUserService.getViewObjectList(queryWrapper, null, IamUserVO.class);
        if (V.isEmpty(iamUserList)) {
            return new JsonResult(Status.FAIL_OPERATION, "用户列表为空，导出失败");
        }
        String fileName = "用户列表导出_" + D.today() + ".xlsx";
        List<IamUserExportModel> dataList = this.entityList2ExcelList(iamUserList);
        ExcelHelper.exportExcel(response, fileName, IamUserExportModel.class, dataList);
        return null;
    }

    /***
     * 实体列表转excel列表
     * @param userVoList
     * @return
     */
    private List<IamUserExportModel> entityList2ExcelList(List<IamUserVO> userVoList) {
        if (V.isEmpty(userVoList)) {
            return Collections.emptyList();
        }
        List<IamUserExportModel> excelModelList = new ArrayList<>();
        for (IamUserVO vo : userVoList) {
            IamUserExportModel excelModel = new IamUserExportModel();
            BeanUtils.copyProperties(vo, excelModel);
            excelModel.setGender(vo.getGenderLabel());
            excelModelList.add(excelModel);
        }
        return excelModelList;
    }

    @Override
    protected FixedHeadExcelListener getExcelDataListener() {
        return new IamUserImportExcelListener();
    }
}
