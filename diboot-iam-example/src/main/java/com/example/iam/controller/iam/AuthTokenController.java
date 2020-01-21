package com.example.iam.controller.iam;

import com.diboot.core.controller.BaseController;
import com.diboot.core.exception.BusinessException;
import com.diboot.core.util.JSON;
import com.diboot.core.vo.JsonResult;
import com.diboot.core.vo.Status;
import com.diboot.iam.annotation.BindPermission;
import com.diboot.iam.auth.AuthServiceFactory;
import com.diboot.iam.config.Cons;
import com.diboot.iam.dto.PwdCredential;
import com.diboot.iam.entity.IamAccount;
import com.diboot.iam.entity.IamUser;
import com.diboot.iam.service.IamAccountService;
import com.diboot.iam.util.IamSecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 建议启用devtools，该文件将由diboot-devtools自动生成
 */
/**
 * IAM认证接口
 * @author www.dibo.ltd
 * @version v2.0
 * @date 2019/12/25
 */
@RestController
@BindPermission(name = "申请token", code = "AUTH")
public class AuthTokenController extends BaseController {

    @Autowired
    private IamAccountService iamAccountService;

    /**
     * 登陆用户，获取token
     * @param credential
     * @return
     * @throws Exception
     */
    @PostMapping("/auth/login")
    public JsonResult login(@RequestBody PwdCredential credential) throws Exception{
        //如需使用自定义的用户类型替代IamUser，指定UserTypeClass
        //credential.setUserTypeClass(Employee.class);
        String authtoken = AuthServiceFactory.getAuthService(Cons.DICTCODE_AUTH_TYPE.PWD.name()).applyToken(credential);
        return new JsonResult(authtoken);
    }

    /**
     * 用户账号注册示例
     * @param account
     * @return
     * @throws Exception
     */
    //@PostMapping("/auth/register")
    public JsonResult register(@Valid @RequestBody IamAccount account) throws Exception {
        // 获取加密前的密码，用于注册后登录
        String password = account.getAuthSecret();
        boolean success = iamAccountService.createEntity(account);
        if(success){
            //注册成功后自动登陆: 注册后密码被加密，重新设置为不加密的密码然后进行登陆
            PwdCredential credential = new PwdCredential(account.getAuthAccount(), password);
            return login(credential);
        }
        return new JsonResult(Status.FAIL_OPERATION);
    }

    /**
     * 退出登陆
     * @return
     * @throws Exception
     */
    @PostMapping("/logout")
    public JsonResult logout() throws Exception{
        IamSecurityUtils.logout();
        return new JsonResult(Status.OK);
    }

    /**
     * 测试获取用户信息
     * @return
     */
    @GetMapping("/auth/userInfo")
    @BindPermission(name = "用户信息", code = "userInfo")
    public JsonResult testUserInfo(){
        //测试获取当前user对象
        IamUser currentUser = IamSecurityUtils.getCurrentUser();
        return new JsonResult(currentUser);
    }

}