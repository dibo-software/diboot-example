package com.example.iam.controller.iam;

import com.diboot.core.controller.BaseCrudRestController;
import com.diboot.core.vo.JsonResult;
import com.diboot.core.vo.Pagination;
import com.diboot.iam.annotation.BindPermission;
import com.diboot.iam.annotation.Operation;
import com.diboot.iam.entity.IamLoginTrace;
import com.diboot.iam.vo.IamLoginTraceVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 建议启用devtools，该文件将由diboot-devtools自动生成
 */
/**
* 登录记录 相关Controller
* @author www.dibo.ltd
* @version 2.0
* @date 2019-12-17
*/
@RestController
@RequestMapping("/iam/loginTrace")
@Slf4j
@BindPermission(name = "登录日志")
public class IamLoginTraceController extends BaseCrudRestController<IamLoginTrace, IamLoginTraceVO> {

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
    public JsonResult getViewObjectListWithMapping(IamLoginTrace entity, Pagination pagination, HttpServletRequest request) throws Exception{
        return super.getViewObjectList(entity, pagination, request);
    }

}