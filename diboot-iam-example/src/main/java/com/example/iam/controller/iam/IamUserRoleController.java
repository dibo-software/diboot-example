package com.example.iam.controller.iam;

import com.diboot.iam.entity.IamUserRole;
import com.diboot.iam.service.IamUserRoleService;
import com.diboot.iam.vo.IamUserRoleVO;
import com.example.iam.controller.BaseCrudMappingRestController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 建议启用devtools，该文件将由diboot-devtools自动生成
 */
/**
* 用户角色关联 相关Controller
* @author www.dibo.ltd
* @version 2.0
* @date 2019-12-17
*/
@Slf4j
@RestController
@RequestMapping("/iam/userRole")
public class IamUserRoleController extends BaseCrudMappingRestController<IamUserRole, IamUserRoleVO> {
    @Autowired
    private IamUserRoleService iamUserRoleService;

}