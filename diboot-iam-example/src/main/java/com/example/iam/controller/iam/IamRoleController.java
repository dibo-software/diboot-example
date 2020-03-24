package com.example.iam.controller.iam;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.diboot.core.controller.BaseCrudRestController;
import com.diboot.core.exception.BusinessException;
import com.diboot.core.util.BeanUtils;
import com.diboot.core.util.V;
import com.diboot.core.vo.JsonResult;
import com.diboot.core.vo.KeyValue;
import com.diboot.core.vo.Pagination;
import com.diboot.core.vo.Status;
import com.diboot.iam.annotation.BindPermission;
import com.diboot.iam.annotation.Operation;
import com.diboot.iam.config.Cons;
import com.diboot.iam.dto.IamRoleFormDTO;
import com.diboot.iam.entity.IamFrontendPermission;
import com.diboot.iam.entity.IamRole;
import com.diboot.iam.service.IamFrontendPermissionService;
import com.diboot.iam.service.IamRolePermissionService;
import com.diboot.iam.service.IamRoleService;
import com.diboot.iam.vo.IamFrontendPermissionListVO;
import com.diboot.iam.vo.IamRoleVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;

/**
 * 建议启用devtools，该文件由diboot-devtools自动生成
 */
/**
* 角色相关Controller
* @author mazc@dibo.ltd
* @version 1.0.1
* @date 2020-03-18
* Copyright © dibo.ltd
*/
@RestController
@RequestMapping("/iam/role")
@Slf4j
@BindPermission(name = "角色")
public class IamRoleController extends BaseCrudRestController<IamRole, IamRoleVO> {

    @Autowired
    private IamRoleService iamRoleService;

    @Autowired
    private IamFrontendPermissionService iamFrontendPermissionService;

    @Autowired
    private IamRolePermissionService iamRolePermissionService;

    /***
    * 查询ViewObject的分页数据
    * <p>
    * url请求参数示例: /list?field=abc&pageSize=20&pageIndex=1&orderBy=id
    * </p>
    * @return
    * @throws Exception
    */
    @GetMapping("/list")
    @BindPermission(name = "查看列表", code = Operation.LIST)
    public JsonResult getViewObjectListMapping(IamRole entity, Pagination pagination, HttpServletRequest request) throws Exception{
        return super.getViewObjectList(entity, pagination, request);
    }

    /***
    * 根据资源id查询ViewObject
    * @param id ID
    * @return
    * @throws Exception
    */
    @GetMapping("/{id}")
    @BindPermission(name = "查看详情", code = Operation.DETAIL)
    public JsonResult getViewObjectMapping(@PathVariable("id")Serializable id, HttpServletRequest request) throws Exception{
        IamRoleVO roleVO = iamRoleService.getViewObject(id, IamRoleVO.class);
        if (V.notEmpty(roleVO.getPermissionList())){
            List<Long> permissionIdList = BeanUtils.collectIdToList(roleVO.getPermissionList());
            List<IamFrontendPermissionListVO> permissionVOList = iamFrontendPermissionService.getViewObjectList(
                Wrappers.<IamFrontendPermission>lambdaQuery().in(IamFrontendPermission::getId, permissionIdList),
                null,
                IamFrontendPermissionListVO.class
            );
            permissionVOList = BeanUtils.buildTree(permissionVOList);
            roleVO.setPermissionVOList(permissionVOList);
        }
        return JsonResult.OK(roleVO);
    }

    /***
    * 新建角色和角色权限关联列表
    * @param roleFormDTO
    * @param request
    * @return
    * @throws Exception
    */
    @PostMapping("/")
    @BindPermission(name = "新建", code = Operation.CREATE)
    public JsonResult createEntityMapping(@Valid @RequestBody IamRoleFormDTO roleFormDTO, HttpServletRequest request) throws Exception {
        return super.createEntity(roleFormDTO, request);
    }

    /***
    * 更新角色和角色权限关联列表
    * @param roleFormDTO
    * @return JsonResult
    * @throws Exception
    */
    @PutMapping("/{id}")
    @BindPermission(name = "更新", code = Operation.UPDATE)
    public JsonResult updateEntityMapping(@PathVariable("id") Long id, @Valid @RequestBody IamRoleFormDTO roleFormDTO, HttpServletRequest request) throws Exception {
        return super.updateEntity(id, roleFormDTO, request);
    }

    /***
    * 根据id删除资源对象
    * @param id
    * @return
    * @throws Exception
    */
    @DeleteMapping("/{id}")
    @BindPermission(name = "删除", code = Operation.DELETE)
    public JsonResult deleteEntityMapping(@PathVariable("id")Serializable id, HttpServletRequest request) throws Exception {
        return super.deleteEntity(id, request);
    }

    /***
    * 获取所有角色键值列表
    * @param request
    * @return
    * @throws Exception
    */
    @GetMapping("kvList")
    public JsonResult getKvList(HttpServletRequest request) throws Exception{
    List<KeyValue> kvList = iamRoleService.getKeyValueList(Wrappers.<IamRole>lambdaQuery().select(IamRole::getId, IamRole::getName));
        return JsonResult.OK(kvList);
    }

    /***
    * 检查编码是否重复
    * @param id
    * @param code
    * @param request
    * @return
    */
    @GetMapping("/checkCodeDuplicate")
    public JsonResult checkCodeDuplicate(@RequestParam(required = false) Long id, @RequestParam String code, HttpServletRequest request) {
        if (V.notEmpty(code)) {
            LambdaQueryWrapper<IamRole> wrapper = Wrappers.<IamRole>lambdaQuery()
                .select(IamRole::getId).eq(IamRole::getCode, code);
            if (V.notEmpty(id)) {
                wrapper.ne(IamRole::getId, id);
            }
            boolean exists = iamRoleService.exists(wrapper);
            if (exists) {
                return JsonResult.FAIL_VALIDATION("编码已存在: "+code);
            }
        }
        return JsonResult.OK();
    }

    @Override
    protected String beforeUpdate(Object entity) throws Exception {
        IamRoleFormDTO roleFormDTO = (IamRoleFormDTO) entity;
        if (Cons.ROLE_SUPER_ADMIN.equals(roleFormDTO.getCode())){
            throw new BusinessException(Status.FAIL_OPERATION, "不能更新超级管理员角色");
        }
        return null;
    }

    @Override
    protected String beforeDelete(Object entity) throws Exception {
        IamRole role = (IamRole)entity;
        if (Cons.ROLE_SUPER_ADMIN.equals(role.getCode())){
            throw new BusinessException(Status.FAIL_OPERATION, "不能删除超级管理员角色");
        }
        return null;
    }

    @Override
    protected void afterCreated(Object entity) throws Exception {
        IamRoleFormDTO roleFormDTO = (IamRoleFormDTO) entity;
        iamRolePermissionService.createRolePermissionRelations(roleFormDTO.getId(), roleFormDTO.getPermissionIdList());
    }

    @Override
    protected void afterUpdated(Object entity) throws Exception {
        IamRoleFormDTO roleFormDTO = (IamRoleFormDTO) entity;
        iamRolePermissionService.updateRolePermissionRelations(roleFormDTO.getId(), roleFormDTO.getPermissionIdList());
    }
}