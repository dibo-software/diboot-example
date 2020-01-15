package com.example.iam.controller.iam;

import com.diboot.core.vo.JsonResult;
import com.diboot.core.vo.Status;
import com.diboot.iam.annotation.BindPermission;
import com.diboot.iam.entity.IamAccount;
import com.diboot.iam.entity.IamUser;
import com.diboot.iam.service.IamUserService;
import com.diboot.iam.util.IamSecurityUtils;
import com.diboot.iam.vo.IamAccountVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import com.example.iam.controller.BaseCrudMappingRestController;

/**
 * 建议启用devtools，该文件将由diboot-devtools自动生成
 */
/**
 * 认证用户 相关Controller
 * @author www.dibo.ltd
 * @version 2.0
 * @date 2019-12-03
 */
@RestController
@RequestMapping("/iam/account")
@Slf4j
@BindPermission(name = "登录账号")
public class IamAccountController extends BaseCrudMappingRestController<IamAccount, IamAccountVO> {

    @Autowired
    private IamUserService iamUserService;

    /***
     * 获取登录用户信息
     * @param request
     * @return
     * @throws Exception
     */
    @GetMapping("/getInfo")
    public JsonResult getInfo(HttpServletRequest request) throws Exception{
        Map<String, Object> data = new HashMap<String, Object>();
        // 获取当前用户对象
        IamUser currentUser = IamSecurityUtils.getCurrentUser();
        data.put("name", currentUser.getRealname());
        data.put("roleList", iamUserService.getAllRoleVOList(currentUser));
        return new JsonResult(Status.OK, data);
    }
}
