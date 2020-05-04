package com.coding.pojo.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

@Data
public class UserRegisterParam {
    /**
     * 用户名
     */
    @NotBlank
    @ApiModelProperty(value = "管理员账号",example = "zzz")
    private String username;


    /**
     * 密码
     */
    @ApiModelProperty(value = "管理员密码",example = "zzz")
    private String password;
}
