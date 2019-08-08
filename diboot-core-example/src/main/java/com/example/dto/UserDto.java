package com.example.dto;

import com.example.entity.User;
import com.example.query.BindQuery;
import com.example.query.Comparison;

import java.io.Serializable;
import java.util.Date;

/**
 * <Description>
 *
 * @author Mazhicheng
 * @version v2.0
 * @date 2019/08/06
 */
public class UserDto extends User implements Serializable {

    private static final String UUID = "1233";

    public static final String TTT = "223";

    @BindQuery(comparison = Comparison.EQ)
    private Long departmentId;

    @BindQuery(comparison = Comparison.STARTSWITH)
    private String username;

    @BindQuery(comparison = Comparison.IN, field = "gender")
    private String[] genders;

    @BindQuery(comparison = Comparison.BETWEEN_BEGIN, field = "create_time")
    private Date createTimeBegin;

    @BindQuery(comparison = Comparison.BETWEEN_END, field = "create_time")
    private Date createTimeEnd;

    //@BindQuery(comparison = Comparison.BETWEEN, field = "create_time")
    private Date[] createTimes;

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

    public Date[] getCreateTimes() {
        return createTimes;
    }

    public void setCreateTimes(Date[] createTime) {
        this.createTimes = createTime;
    }
}
