package com.example.iam.controller.iam;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.diboot.core.controller.BaseCrudRestController;
import com.diboot.core.util.BeanUtils;
import com.diboot.core.util.V;
import com.diboot.core.vo.JsonResult;
import com.diboot.core.vo.KeyValue;
import com.diboot.iam.annotation.BindPermission;
import com.diboot.iam.annotation.Log;
import com.diboot.iam.annotation.Operation;
import com.diboot.iam.annotation.process.ApiPermissionCache;
import com.diboot.iam.config.Cons;
import com.diboot.iam.dto.IamResourcePermissionDTO;
import com.diboot.iam.entity.IamResourcePermission;
import com.diboot.iam.service.IamResourcePermissionService;
import com.diboot.iam.vo.IamResourcePermissionListVO;
import com.diboot.iam.vo.IamResourcePermissionVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 启用devtools，该文件由diboot-devtools自动生成
 */
/**
* 前端资源权限相关Controller
* @author MyName
* @version 1.0
* @date 2020-11-28
* Copyright © MyCompany
*/
@RestController
@RequestMapping("/iam/frontendPermission")
@Slf4j
@Api(tags = {"前端资源权限"})
@BindPermission(name = "前端资源权限")
public class IamResourcePermissionController extends BaseCrudRestController<IamResourcePermission> {

    @Autowired
    private IamResourcePermissionService iamResourcePermissionService;

    /***
    * 查询ViewObject的分页数据
    * <p>
    * url请求参数示例: /list?field=abc&pageSize=20&pageIndex=1&orderBy=id
    * </p>
    * @return
    * @throws Exception
    */
    @ApiOperation(value = "获取列表分页数据")
    @Log(operation = Operation.LABEL_LIST)
    @BindPermission(name = Operation.LABEL_LIST, code = Operation.CODE_LIST)
    @GetMapping("/list")
    public JsonResult getViewObjectListMapping(IamResourcePermission entity) throws Exception{
        QueryWrapper<IamResourcePermission> queryWrapper = super.buildQueryWrapper(entity);
        queryWrapper.lambda().orderByDesc(IamResourcePermission::getSortId, IamResourcePermission::getId);
        List<IamResourcePermissionListVO> voList = iamResourcePermissionService.getViewObjectList(queryWrapper, null, IamResourcePermissionListVO.class);
        voList = BeanUtils.buildTree(voList);
        return JsonResult.OK(voList);
    }

    /***
    * 根据资源id查询ViewObject
    * @param id ID
    * @return
    * @throws Exception
    */
    @ApiOperation(value = "根据ID获取详情数据")
    @Log(operation = Operation.LABEL_DETAIL)
    @BindPermission(name = Operation.LABEL_DETAIL, code = Operation.CODE_DETAIL)
    @GetMapping("/{id}")
    public JsonResult getViewObjectMapping(@PathVariable("id")Long id) throws Exception{
        return super.getViewObject(id, IamResourcePermissionVO.class);
    }

    /***
    * 新建菜单项、按钮/权限列表
    * @param iamResourcePermissionDTO
    * @return
    * @throws Exception
    */
    @ApiOperation(value = "新建数据")
    @Log(operation = Operation.LABEL_CREATE)
    @BindPermission(name = Operation.LABEL_CREATE, code = Operation.CODE_CREATE)
    @PostMapping("/")
    public JsonResult createEntityMapping(@Valid @RequestBody IamResourcePermissionDTO iamResourcePermissionDTO) throws Exception {
        iamResourcePermissionService.createMenuAndPermissions(iamResourcePermissionDTO);
        return JsonResult.OK();
    }

    /***
    * 更新用户、账号和用户角色关联列表
    * @param iamResourcePermissionDTO
    * @return JsonResult
    * @throws Exception
    */
    @ApiOperation(value = "根据ID更新数据")
    @Log(operation = Operation.LABEL_UPDATE)
    @BindPermission(name = Operation.LABEL_UPDATE, code = Operation.CODE_UPDATE)
    @PutMapping("/{id}")
    public JsonResult updateEntityMapping(@PathVariable("id") Long id, @Valid @RequestBody IamResourcePermissionDTO iamResourcePermissionDTO) throws Exception {
        iamResourcePermissionService.updateMenuAndPermissions(iamResourcePermissionDTO);
        return JsonResult.OK();
    }

    /***
    * 删除用户、账号和用户角色关联列表
    * @param id
    * @return
    * @throws Exception
    */
    @ApiOperation(value = "根据ID删除数据")
    @Log(operation = Operation.LABEL_DELETE)
    @BindPermission(name = Operation.LABEL_DELETE, code = Operation.CODE_DELETE)
    @DeleteMapping("/{id}")
    public JsonResult deleteEntityMapping(@PathVariable("id")Long id) throws Exception {
        iamResourcePermissionService.deleteMenuAndPermissions(id);
        return JsonResult.OK();
    }

    /***
    * 加载更多数据
    * @return
    * @throws Exception
    */
    @ApiOperation(value = "获取更多关联数据")
    @GetMapping("/attachMore")
    public JsonResult attachMore(ModelMap modelMap) throws Exception{
        // 获取关联表数据IamResourcePermission的树状列表
        List<IamResourcePermissionListVO> menuList = iamResourcePermissionService.getViewObjectList(
            Wrappers.<IamResourcePermission>lambdaQuery().eq(IamResourcePermission::getDisplayType, Cons.RESOURCE_PERMISSION_DISPLAY_TYPE.MENU.name()),
            null,
            IamResourcePermissionListVO.class
        );
        menuList = BeanUtils.buildTree(menuList);
        modelMap.put("menuList", menuList);
        // 获取关联数据字典USER_STATUS的KV
        List<KeyValue> frontendPermissionCodeKvList = dictionaryService.getKeyValueList(Cons.DICTTYPE.RESOURCE_PERMISSION_CODE.name());
        modelMap.put("frontendPermissionCodeKvList", frontendPermissionCodeKvList);
        return JsonResult.OK(modelMap);
    }

    /**
    * 列表排序
    * @param permissionList
    * @return
    * @throws Exception
    */
    @ApiOperation(value = "更新列表排序")
    @PostMapping("/sortList")
    @BindPermission(name="列表排序", code = Operation.UPDATE)
    public JsonResult sortList(@RequestBody List<IamResourcePermission> permissionList) throws Exception {
        iamResourcePermissionService.sortList(permissionList);
        return JsonResult.OK().msg("更新成功");
    }

    /***
    * api接口列表（供前端选择）
    * @return
    * @throws Exception
    */
    @ApiOperation(value = "授权接口列表(前端备选)")
    @GetMapping("/apiList")
    public JsonResult apiList() throws Exception{
        return JsonResult.OK(ApiPermissionCache.getApiPermissionVoList());
    }

    /***
    * 检查菜单编码是否重复
    * @param id
    * @param code
    * @return
    */
    @ApiOperation(value = "检查编码是否重复")
    @GetMapping("/checkCodeDuplicate")
    public JsonResult checkCodeDuplicate(@RequestParam(required = false) Long id, @RequestParam String code) {
        if (V.notEmpty(code)) {
            LambdaQueryWrapper<IamResourcePermission> wrapper = Wrappers.<IamResourcePermission>lambdaQuery()
                .select(IamResourcePermission::getId)
                .eq(IamResourcePermission::getResourceCode, code)
                .eq(IamResourcePermission::getDisplayType, Cons.RESOURCE_PERMISSION_DISPLAY_TYPE.MENU.name());
            if (V.notEmpty(id)) {
                wrapper.ne(IamResourcePermission::getId, id);
            }
            boolean exists = iamResourcePermissionService.exists(wrapper);
            if (exists) {
                return JsonResult.FAIL_VALIDATION("编码已存在: "+code);
            }
        }
        return JsonResult.OK();
    }

}