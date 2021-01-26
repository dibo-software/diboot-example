package com.example.iam.controller;

import com.diboot.core.entity.Dictionary;
import com.diboot.core.util.S;
import com.diboot.core.vo.JsonResult;
import com.diboot.core.vo.Status;
import com.diboot.file.controller.BaseFileController;
import com.diboot.file.dto.UploadFileFormDTO;
import com.diboot.file.entity.UploadFile;
import com.diboot.file.util.HttpHelper;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

/**
 * 启用devtools，该文件由diboot-devtools自动生成
 */
/**
* 文件上传相关Controller
*
* @author MyName
* @version 1.0
* @date 2020-11-28
* * Copyright © MyCompany
*/
@Api(tags = {"文件上传"})
@RestController
@RequestMapping("/uploadFile")
@Slf4j
public class UploadFileController extends BaseFileController {

    /**
     * 上传文件
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping("/upload")
    public JsonResult uploadMapping(@RequestParam("file") MultipartFile file) throws Exception{
        return super.uploadFile(file, Dictionary.class);
    }

    /**
     * 上传文件 by dto
     *
     * @param uploadFileFormDTO
     * @return
     * @throws Exception
     */
    @PostMapping("/upload/dto")
    public JsonResult uploadMapping(UploadFileFormDTO uploadFileFormDTO) throws Exception{
        return super.uploadFile(uploadFileFormDTO);
    }
    
    /**
     * 下载文件
     **/
    @GetMapping("/download/{fileUuid}")
    public JsonResult download(@PathVariable("fileUuid")String fileUuid, HttpServletResponse response) throws Exception {
        if(S.contains(fileUuid, ".")){
            fileUuid = S.substringBefore(fileUuid, ".");
        }
        UploadFile uploadFile = uploadFileService.getEntity(fileUuid);
        if(uploadFile == null){
            return new JsonResult(Status.FAIL_VALIDATION, "文件不存在");
        }
        // 下载
        HttpHelper.downloadLocalFile(uploadFile.getStoragePath(), uploadFile.getFileName(), response);
        return null;
    }
    
    /**
     * 显示图片
     *
     * @param fileUuid
     * @param response
     * @return
     * @throws Exception
     */
    @GetMapping("/download/{fileUuid}/image")
    public JsonResult writeImage(@PathVariable("fileUuid") String fileUuid, HttpServletResponse response) throws Exception {
        if (S.contains(fileUuid, ".")) {
            fileUuid = S.substringBefore(fileUuid, ".");
        }
        UploadFile uploadFile = uploadFileService.getEntity(fileUuid);
        if (uploadFile == null) {
            return new JsonResult(Status.FAIL_VALIDATION, "图片不存在");
        }
        IOUtils.copy(FileUtils.openInputStream(new File(uploadFile.getStoragePath())), response.getOutputStream());
        return null;
    }
    
    /**
     * <h3>获取文件通用接口</h3>
     * <p>
     * 其中当relObjField不传递的时候，表示获取当前业务ID和业务类型下的所有文件<br/>
     * 当传递relObjField的时候，获取指定类型的文件
     * </p>
     *
     * @param relObjId   业务ID   <strong style="color:red;">必传字段</strong>
     * @param relObjType 业务类型 <strong style="color:red;">必传字段</strong>
     * @param relObjField 对应的具体类型   <strong style="color:blue;">非必传字段(同一种业务下可能有多种文件)</strong>
     * @return {@link List <UploadFile>} 返回文件对象的集合
     * @throws Exception
     */
    @GetMapping(value = {"/getList/{relObjId}/{relObjType}", "/getList/{relObjId}/{relObjType}/{relObjField}"})
    public JsonResult getFileList(@PathVariable("relObjId") Object relObjId, @PathVariable("relObjType") String relObjType,
                              @PathVariable(value = "relObjField", required = false) String relObjField) throws Exception {
        List<UploadFile> uploadFiles = getUploadFileList(relObjId, relObjType, relObjField);
        return JsonResult.OK(uploadFiles);
    }
}