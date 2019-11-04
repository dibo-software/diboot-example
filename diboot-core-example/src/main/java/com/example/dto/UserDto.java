package com.example.dto;

import com.diboot.core.binding.query.BindQuery;
import com.diboot.core.binding.query.Comparison;
import com.example.entity.User;

import java.util.Date;

/**
 * DTO测试样例，用于自动绑定QueryWrapper
 * @author Mazc
 * @version v2.0
 * @date 2019/08/06
 */
public class UserDto extends User{

    @BindQuery(comparison = Comparison.STARTSWITH)
    private String username;

    @BindQuery(comparison = Comparison.IN, field = "gender")
    private String[] genders;

    @BindQuery(comparison = Comparison.BETWEEN_BEGIN, field = "create_time")
    private Date createTimeBegin;

    @BindQuery(comparison = Comparison.BETWEEN_END, field = "create_time")
    private Date createTimeEnd;

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    public String[] getGenders() {
        return genders;
    }

    public void setGenders(String[] genders) {
        this.genders = genders;
    }

    public Date getCreateTimeBegin() {
        return createTimeBegin;
    }

    public void setCreateTimeBegin(Date createTimeBegin) {
        this.createTimeBegin = createTimeBegin;
    }

    public Date getCreateTimeEnd() {
        return createTimeEnd;
    }

    public void setCreateTimeEnd(Date createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }

}
