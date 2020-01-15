package com.example.iam.controller.iam;

import com.diboot.iam.entity.IamRolePermission;
import com.diboot.iam.service.IamRolePermissionService;
import com.diboot.iam.vo.IamRolePermissionVO;
import com.example.iam.controller.BaseCrudMappingRestController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 建议启用devtools，该文件将由diboot-devtools自动生成
 */
/**
* 角色权限关联 相关Controller
* @author www.dibo.ltd
* @version 2.0
* @date 2019-12-03
*/
@Slf4j
@RestController
@RequestMapping("/iam/rolePermission")
public class IamRolePermissionController extends BaseCrudMappingRestController<IamRolePermission, IamRolePermissionVO> {
    @Autowired
    private IamRolePermissionService iamRolePermissionService;

}