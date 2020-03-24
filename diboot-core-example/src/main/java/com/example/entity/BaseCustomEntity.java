package com.example.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.diboot.core.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 用户Entity
 * @author www.dibo.ltd
 * @version v2.0
 * @date 2019/1/30
 */
@Getter
@Setter
@Accessors(chain = true)
public class BaseCustomEntity extends BaseEntity {
    private static final long serialVersionUID = 305076134404519434L;

    @TableLogic
    @JSONField(serialize = false)
    @TableField("deleted")
    private boolean deleted = false;

}
