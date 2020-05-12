package com.coding.domain;

import java.io.Serializable;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Table(name="user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "useracct")
    @ApiModelProperty(value = "普通用户账号",example = "zhangsan")
    private String useracct;

    @Column(name = "userpwd")
    @ApiModelProperty(value = "普通用户密码",example = "rggf659sdf2")
    private String userpwd;


}
