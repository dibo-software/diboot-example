package com.example.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.diboot.core.entity.BaseEntity;
import lombok.Data;
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
public class Role extends BaseEntity {
    private static final long serialVersionUID = 3701095453152116088L;

    @TableField(exist = false)
    private Long id;

    @TableId(type=IdType.UUID)
    private String uid;

    @TableField
    private String name;

    @TableField
    private String code;
}