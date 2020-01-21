package com.example.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.diboot.core.entity.BaseEntity;
import com.example.entity.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 角色Entity
 * @author www.dibo.ltd
 * @version v2.0
 * @date 2019/1/30
 */
@Getter
@Setter
@Accessors(chain = true)
public class RoleVO extends Role {
    private static final long serialVersionUID = 123L;

}
