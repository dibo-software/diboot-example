package com.example.iam.controller.iam;

import com.diboot.iam.annotation.BindPermission;
import com.diboot.iam.entity.IamPermission;
import com.diboot.iam.service.IamPermissionService;
import com.diboot.iam.vo.IamPermissionVO;
import com.example.iam.controller.BaseCrudMappingRestController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 建议启用devtools，该文件将由diboot-devtools自动生成
 */
/**
* 权限 相关Controller
* @author www.dibo.ltd
* @version 2.0
* @date 2019-12-03
*/
@Slf4j
@RestController
@RequestMapping("/iam/permission")
@BindPermission(name = "权限")
public class IamPermissionController extends BaseCrudMappingRestController<IamPermission, IamPermissionVO> {
    @Autowired
    private IamPermissionService iamPermissionService;

}