package com.example.iam.controller.iam;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.diboot.core.util.V;
import com.diboot.core.vo.JsonResult;
import com.diboot.core.vo.KeyValue;
import com.diboot.core.vo.Status;
import com.diboot.iam.annotation.BindPermission;
import com.diboot.iam.entity.IamRole;
import com.diboot.iam.service.IamRoleService;
import com.diboot.iam.vo.IamRoleVO;
import com.example.iam.controller.BaseCrudMappingRestController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 建议启用devtools，该文件将由diboot-devtools自动生成
 */
/**
* 角色 相关Controller
* @author www.dibo.ltd
* @version 2.0
* @date 2019-12-03
*/
@RestController
@RequestMapping("/iam/role")
@Slf4j
@BindPermission(name = "角色")
public class IamRoleController extends BaseCrudMappingRestController<IamRole, IamRoleVO> {

    @Autowired
    private IamRoleService iamRoleService;

    /***
     * 获取所有键值列表
     * @param request
     * @return
     * @throws Exception
     */
    @GetMapping("kvList")
    public JsonResult getKvList(HttpServletRequest request) throws Exception{
        List<KeyValue> kvList = iamRoleService.getKeyValueList(Wrappers.<IamRole>lambdaQuery().select(IamRole::getId, IamRole::getName));
        return new JsonResult(Status.OK, kvList);
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
            LambdaQueryWrapper<IamRole> wrapper = new QueryWrapper<IamRole>().lambda()
                    .select(IamRole::getId).eq(IamRole::getCode, code);
            if (V.notEmpty(id)) {
                wrapper.ne(IamRole::getId, id);
            }
            boolean exists = iamRoleService.exists(wrapper);
            if (exists) {
                return new JsonResult(Status.FAIL_VALIDATION, "编码已存在: "+code);
            }
        }
        return new JsonResult(Status.OK);
    }
}