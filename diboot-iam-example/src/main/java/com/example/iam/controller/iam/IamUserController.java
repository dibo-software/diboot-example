package com.example.iam.controller.iam;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.diboot.core.service.DictionaryService;
import com.diboot.core.util.BeanUtils;
import com.diboot.core.util.V;
import com.diboot.core.vo.JsonResult;
import com.diboot.core.vo.KeyValue;
import com.diboot.core.vo.Pagination;
import com.diboot.iam.annotation.BindPermission;
import com.diboot.iam.config.Cons;
import com.diboot.iam.entity.IamUser;
import com.diboot.iam.service.IamUserService;
import com.diboot.iam.vo.IamUserVO;
import com.example.iam.controller.BaseCrudMappingRestController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 建议启用devtools，该文件将由diboot-devtools自动生成
 */
/**
 * 系统用户 相关Controller
 * @author www.dibo.ltd
 * @version 2.0
 * @date 2019-12-17
 */
@Slf4j
@RestController
@RequestMapping("/iam/user")
@BindPermission(name = "用户")
public class IamUserController extends BaseCrudMappingRestController<IamUser, IamUserVO> {

    @Autowired
    private IamUserService iamUserService;

    @Autowired
    private DictionaryService dictionaryService;

    /**
     * 获取指定节orgId的用户列表
     * @return
     * @throws Exception
     */
    @GetMapping("/getUserList/{orgId}")
    public JsonResult getUserList(@PathVariable("orgId") Long orgId, IamUser iamUser, Pagination pagination, HttpServletRequest request) throws Exception {
        QueryWrapper<IamUser> wrapper = super.buildQueryWrapper(iamUser, request);
        if (orgId != null && !V.equals(orgId, 0L)) {
            wrapper.lambda().eq(IamUser::getOrgId, orgId);
        }
        return super.getEntityListWithPaging(wrapper, pagination);
    }

    /**
     * 加载更多数据
     * @return
     * @throws Exception
     */
    @GetMapping("/attachMore")
    public JsonResult attachMore(HttpServletRequest request, ModelMap modelMap) throws Exception {
        // 获取关联数据字典USER_STATUS的KV
        List<KeyValue> userStatusKvList = dictionaryService.getKeyValueList(Cons.DICTTYPE.USER_STATUS.name());
        modelMap.put("userStatusKvList", userStatusKvList);
        Map<String, KeyValue> userStatusKvMap = BeanUtils.convertToStringKeyObjectMap(userStatusKvList, BeanUtils.convertToFieldName(KeyValue::getV));
        modelMap.put("userStatusKvMap", userStatusKvMap);
        // 获取关联数据字典ORG_TYPE的KV
        List<KeyValue> genderKvList = dictionaryService.getKeyValueList(Cons.DICTTYPE.GENDER.name());
        modelMap.put("genderKvList", genderKvList);
        Map<String, KeyValue> genderKvMap = BeanUtils.convertToStringKeyObjectMap(genderKvList, BeanUtils.convertToFieldName(KeyValue::getV));
        modelMap.put("genderKvMap", genderKvMap);
        return new JsonResult(modelMap);
    }
}
