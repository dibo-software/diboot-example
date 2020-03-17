package com.example.controller;

import com.example.entity.Role;
import com.example.vo.RoleVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
