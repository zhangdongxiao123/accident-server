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
@Table(name="airdetail")
public class Airdetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "airid")
    @ApiModelProperty(value = "事故ID",example = "001")
    private String airid;

    @Column(name = "airname")
    @ApiModelProperty(value = "事故名称",example = "zhangsan")
    private String airname;

    @Column(name = "airtype")
    @ApiModelProperty(value = "事故类型",example = "隐患")
    private String airtype;

    @Column(name = "airwhen")
    @ApiModelProperty(value = "事故时间",example = "2019年1月1日")
    private String airwhen;

    @Column(name = "airwhere")
    @ApiModelProperty(value = "事故地点",example = "东京")
    private String airwhere;

    @Column(name = "airwhy")
    @ApiModelProperty(value = "事故原因",example = "环境原因")
    private String airwhy;

    @Column(name = "airhow")
    @ApiModelProperty(value = "事故详情",example = "因工作人员失误，险些造成事故")
    private String airhow;

    @Column(name = "airimg")
    @ApiModelProperty(value = "事故图片",example = "1.jpg")
    private String airimg;


}
