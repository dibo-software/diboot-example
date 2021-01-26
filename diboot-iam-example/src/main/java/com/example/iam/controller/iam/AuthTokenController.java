package com.example.iam.controller.iam;

import com.diboot.core.controller.BaseController;
import com.diboot.core.vo.JsonResult;
import com.diboot.iam.annotation.BindPermission;
import com.diboot.iam.annotation.Log;
import com.diboot.iam.auth.AuthServiceFactory;
import com.diboot.iam.config.Cons;
import com.diboot.iam.dto.PwdCredential;
import com.diboot.iam.entity.IamUser;
import com.diboot.iam.service.IamUserService;
import com.diboot.iam.util.IamSecurityUtils;
import com.diboot.iam.vo.IamRoleVO;
import com.wf.captcha.ArithmeticCaptcha;
import com.wf.captcha.utils.CaptchaUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 启用devtools，该文件由diboot-devtools自动生成
 */
/**
* IAM身份认证/申请Token接口
* @author MyName
* @version 1.0
* @date 2020-11-28
* Copyright © MyCompany
*/
@RestController
@Slf4j
@Api(tags = {"身份认证"})
@BindPermission(name = "身份认证", code = "AUTH")
public class AuthTokenController extends BaseController {
    @Autowired
    private IamUserService iamUserService;

    /**
    * 获取验证码
    */
    @ApiOperation(value = "获取验证码")
    @GetMapping("/auth/captcha")
    public void captcha(HttpServletResponse response) throws Exception {
        ArithmeticCaptcha captcha = new ArithmeticCaptcha();
        CaptchaUtil.out(captcha, request, response);
    }

    /**
    * 用户登录获取token
    * @param credential
    * @return
    * @throws Exception
    */
    @ApiOperation(value = "登录获取token")
    @PostMapping("/auth/login")
    public JsonResult login(@RequestBody PwdCredential credential) throws Exception{
        // 验证码校验
        if (!CaptchaUtil.ver(credential.getCaptcha(), request)) {
            CaptchaUtil.clear(request);
            return JsonResult.FAIL_VALIDATION("验证码错误");
        }
        String authtoken = AuthServiceFactory.getAuthService(Cons.DICTCODE_AUTH_TYPE.PWD.name()).applyToken(credential);
        return JsonResult.OK(authtoken);
    }

    /**
    * 注销/退出
    * @return
    * @throws Exception
    */
    @ApiOperation(value = "退出登录")
		@Log(businessObj = "LoginUser", operation = "退出")
    @PostMapping("/logout")
    public JsonResult logout() throws Exception{
        IamSecurityUtils.logout();
        return JsonResult.OK();
    }

    /**
    * 获取用户角色权限信息
    * @return
    */
    @ApiOperation(value = "获取用户角色权限信息")
    @GetMapping("/auth/userInfo")
    public JsonResult getUserInfo(){
        Map<String, Object> data = new HashMap<>();
        // 获取当前登录用户对象
        IamUser currentUser = IamSecurityUtils.getCurrentUser();
        if(currentUser == null){
            return JsonResult.OK();
        }
        data.put("name", currentUser.getRealname());
        // 角色权限数据
        IamRoleVO roleVO = iamUserService.buildRoleVo4FrontEnd(currentUser);
        data.put("role", roleVO);
        return JsonResult.OK(data);
    }

    /***
    * 心跳接口
    * @return
    * @throws Exception
    */
    @ApiOperation(value = "心跳接口")
    @PostMapping("/iam/ping")
    public JsonResult ping() throws Exception{
        return JsonResult.OK();
    }
}