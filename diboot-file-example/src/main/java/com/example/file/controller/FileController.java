package com.example.file.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.diboot.core.entity.Dictionary;
import com.diboot.core.vo.JsonResult;
import com.diboot.core.vo.Pagination;
import com.diboot.core.vo.Status;
import com.diboot.file.controller.BaseFileController;
import com.diboot.file.entity.UploadFile;
import com.diboot.file.util.HttpHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 文件上传下载相关Controller
 * @author www.dibo.ltd
 * @version v2.0
 * @date 2019/1/19
 */
@RestController
@RequestMapping("/file")
@Slf4j
public class FileController extends BaseFileController {

    /**
     * 上传文件
     **/
    @PostMapping("/upload")
    public JsonResult upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception{
        return super.uploadFile(file, Dictionary.class, request);
    }

    /**
     * 下载文件
     **/
    @GetMapping("/download/{fileUuid}")
    public JsonResult download(@PathVariable("fileUuid")String fileUuid, HttpServletResponse response) throws Exception {
        UploadFile uploadFile = uploadFileService.getEntity(fileUuid);
        if(uploadFile == null){
            return new JsonResult(Status.FAIL_VALIDATION, "文件不存在");
        }
        // 下载
        HttpHelper.downloadLocalFile(uploadFile.getStoragePath(), "导出文件.txt", response);
        return null;
    }

    /**
     * 上传文件列表
     */
    @GetMapping("/list")
    public JsonResult getVOList(UploadFile uploadFile, Pagination pagination, HttpServletRequest request) throws Exception{
        QueryWrapper<UploadFile> queryWrapper = super.buildQueryWrapper(uploadFile, request);
        return super.getEntityListWithPaging(queryWrapper, pagination);
    }

    @Override
    protected <T> UploadFile saveFile(MultipartFile file, Class<T> entityClass, HttpServletRequest request) throws Exception {
        // 自定义文件存储
        return super.saveFile(file, entityClass, request);
    }

}
