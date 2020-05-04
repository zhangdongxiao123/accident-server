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
@Table(name="reason")
public class Reason implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "reaid")
    @ApiModelProperty(value = "原因ID",example = "01")
    private String reaid;

    @Column(name = "reaname")
    @ApiModelProperty(value = "原因类型",example = "环境原因")
    private String reaname;

    @Column(name = "reahow")
    @ApiModelProperty(value = "原因详情",example = "天气不好")
    private String reahow;


}
