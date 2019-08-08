package com.example.controller;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.toolkit.JdbcUtils;
import com.diboot.core.binding.QueryBuilder;
import com.diboot.core.controller.BaseCrudRestController;
import com.diboot.core.service.BaseService;
import com.diboot.core.vo.JsonResult;
import com.diboot.core.vo.Status;
import com.example.dto.UserDto;
import com.example.entity.User;
import com.example.service.UserService;
import com.example.vo.NewPagination;
import com.example.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * User相关Controller
 * @author Mazhicheng
 * @version 2019/05/11
 * Copyright © www.dibo.ltd
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseCrudRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private Environment env;

    /***
     * 查询ViewObject的分页数据 (此为继承父类方法的使用样例，更多自定义案例请参考DepartmentController)
     * <p>
     * url参数示例: /list?_pageSize=20&_pageIndex=1&_orderBy=username&gender=M
     * </p>
     * @return
     * @throws Exception
     */
    @GetMapping("/list")
    public JsonResult getVOList(HttpServletRequest request) throws Exception{
        QueryWrapper<User> queryWrapper = buildQuery(request);
        String jdbcUrl = env.getProperty("spring.datasource.url");
        DbType dbType = JdbcUtils.getDbType(jdbcUrl);
        System.out.println(jdbcUrl + ", "+dbType.getDb());

        return super.getVOListWithPaging(request, queryWrapper, UserVO.class);
    }


    @GetMapping("/listDto")
    public JsonResult getVOListWithDTO(UserDto userDto, NewPagination pagination, HttpServletRequest request) throws Exception{
        QueryWrapper<User> queryWrapper = QueryBuilder.toQueryWrapper(userDto);
        // 构建分页
        //Pagination pagination = buildPagination(request);
        // 查询当前页的Entity主表数据
        List entityList = userService.getEntityList(queryWrapper, pagination);
        // 自动转换VO中注解绑定的关联
        //List<UserVO> voList = super.convertToVoAndBindRelations(entityList, UserVO.class);
        // 返回结果
        return new JsonResult(Status.OK, entityList).bindPagination(pagination);
    }

    /***
     * 查询ViewObject的分页数据 (此为继承父类方法的使用样例，更多自定义案例请参考DepartmentController)
     * <p>
     * url参数示例: /listAll?_orderBy=username&gender=M
     * </p>
     * @return
     * @throws Exception
     */
    @GetMapping("/listAll")
    public JsonResult getAllVOList(HttpServletRequest request) throws Exception{
        QueryWrapper<User> queryWrapper = buildQuery(request);
        return super.getVOListWithPaging(request, queryWrapper, UserVO.class);
    }

    @Override
    protected BaseService getService() {
        return userService;
    }

}
