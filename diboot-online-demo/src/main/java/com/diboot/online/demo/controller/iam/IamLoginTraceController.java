package com.diboot.online.demo.controller.iam;

import com.diboot.core.controller.BaseCrudRestController;
import com.diboot.core.vo.JsonResult;
import com.diboot.core.vo.KeyValue;
import com.diboot.core.vo.Pagination;
import com.diboot.iam.annotation.BindPermission;
import com.diboot.iam.annotation.Operation;
import com.diboot.iam.config.Cons;
import com.diboot.iam.entity.IamLoginTrace;
import com.diboot.iam.vo.IamLoginTraceVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* 登录日志
* @author yangzhao
* @version 2.0.0
* @date 2020-03-24
* Copyright © diboot.com
*/
@RestController
@RequestMapping("/iam/loginTrace")
@BindPermission(name = "登录日志")
public class IamLoginTraceController extends BaseCrudRestController<IamLoginTrace> {
    private static final Logger log = LoggerFactory.getLogger(IamLoginTraceController.class);

    /***
    * 查询分页数据
    * @return
    * @throws Exception
    */
    @GetMapping("/list")
    @BindPermission(name = "查看列表", code = Operation.CODE_LIST)
    public JsonResult getViewObjectListMapping(IamLoginTrace entity, Pagination pagination) throws Exception{
        return super.getViewObjectList(entity, pagination, IamLoginTraceVO.class);
    }

    /**
    * 加载更多数据
    * @return
    * @throws Exception
    */
    @GetMapping("/attachMore")
    public JsonResult attachMore(HttpServletRequest request, ModelMap modelMap) throws Exception {
        // 获取关联数据字典AUTH_TYPE的KV
        List<KeyValue> authTypeKvList = dictionaryService.getKeyValueList(Cons.DICTTYPE.AUTH_TYPE.name());
        modelMap.put("authTypeKvList", authTypeKvList);
        return JsonResult.OK(modelMap);
    }

}