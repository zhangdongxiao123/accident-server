package com.coding.pojo.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
public class UserRegisterParam {
    /**
     * 用户名
     */
    @NotBlank
    @ApiModelProperty(value = "普通用户账号",example = "zzz")
    private String useracct;


    /**
     * 密码
     */
    @ApiModelProperty(value = "普通用户密码",example = "zzz")
    private String userpwd;
}

