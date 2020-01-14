package com.example.dto;

import com.diboot.core.binding.query.BindQuery;
import com.diboot.core.binding.query.Comparison;
import com.example.entity.User;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * DTO测试样例，用于自动绑定QueryWrapper
 * @author www.dibo.ltd
 * @version v2.0
 * @date 2019/08/06
 */
@Getter @Setter @Accessors(chain = true)
public class UserDto extends User{

    @BindQuery(comparison = Comparison.STARTSWITH)
    private String username;

    @BindQuery(comparison = Comparison.IN, field = "gender")
    private String[] genders;

    @BindQuery(comparison = Comparison.BETWEEN_BEGIN, field = "create_time")
    private Date createTimeBegin;

    @BindQuery(comparison = Comparison.BETWEEN_END, field = "create_time")
    private Date createTimeEnd;

}
