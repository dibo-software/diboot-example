package com.example.iam.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 建议启用devtools，该文件由diboot-devtools自动生成
 */
@Getter
@Setter
@Accessors(chain = true)
public class BaseUserInfoDTO implements Serializable {
    private static final long serialVersionUID = -3223502578145175877L;

    @NotNull(message = "真实姓名不能为空")
    @Length(max = 50, message = "真实姓名长度应小于50")
    private String realname;

    @NotNull(message = "性别不能为空")
    @Length(max = 10, message = "性别长度应小于10")
    private String gender;

    @Length(max = 20, message = "手机号长度应小于20")
    private String mobilePhone;

    @Length(max = 50, message = "Email长度应小于50")
    private String email;


}
