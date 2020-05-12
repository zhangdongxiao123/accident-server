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



//@Data相当于@Getter @Setter @RequiredArgsConstructor @ToString @EqualsAndHashCode这5个注解的合集
@Data
//有多个类有相同的部分属性，把它们定义到父类中，恰好id（数据库主键）也在父类中，那么就会存在部分对象在比较时，它们并不相等，却因为lombok自动生成的equals(Object other) 和 hashCode()方法判定为相等，从而导致出错
@EqualsAndHashCode(callSuper = false)
//fluent的中文含义是流畅的，设置为true，则getter和setter方法的方法名都是基础属性名，且setter方法返回当前对象
@Accessors(chain = true)
@Table(name="airdetail")
public class Airdetail implements Serializable {

    //定义程序序列化ID
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
