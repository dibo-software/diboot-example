package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.diboot.core.vo.JsonResult;
import com.diboot.core.vo.Pagination;
import com.example.dto.UserDto;
import com.example.entity.Role;
import com.example.entity.User;
import com.example.service.UserService;
import com.example.vo.RoleVO;
import com.example.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * User相关Controller
 * @author www.dibo.ltd
 * @version v2.0
 * @date 2019/7/19
 */
@RestController
@RequestMapping("/role")
public class RoleController extends BaseCrudMappingRestController<Role, RoleVO> {

}
