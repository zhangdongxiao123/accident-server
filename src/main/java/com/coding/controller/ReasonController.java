package com.coding.controller;


import com.coding.common.Const;
import com.coding.domain.Airdetail;
import com.coding.domain.Reason;
import com.coding.mapper.ReasonMapper;
import com.coding.pojo.param.ReasonParam;
import com.coding.service.ManagerService;
import com.coding.service.UserService;
import com.guanweiming.common.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import springfox.documentation.annotations.ApiIgnore;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.WeekendSqls;

import java.util.List;
import java.util.UUID;


@Slf4j
@Api(tags = "事故原因接口")
@AllArgsConstructor
@RestController
@RequestMapping(Const.API + "reason")
public class ReasonController {
    private final ReasonMapper reasonMapper;


    @ApiOperation("添加事故原因")
    @PostMapping("add")
    public Result<String> addReason(ReasonParam param) {
        Reason reason = new Reason();
        BeanUtils.copyProperties(param, reason);
        reason.setReaid(UUID.randomUUID().toString());
        reasonMapper.insertSelective(reason);
        return Result.createBySuccess();
    }

    @ApiOperation("更新事故原因")
    @PostMapping("update")
    public Result<String> updateReason(Reason param) {
        reasonMapper.updateByPrimaryKeySelective(param);
        return Result.createBySuccess();
    }

    @ApiOperation("删除事故原因")
    @PostMapping("delete")
    public Result<String> deleteReason(@RequestParam String reaid) {
        reasonMapper.deleteByPrimaryKey(reaid);
        return Result.createBySuccess();
    }

//    @ApiOperation(value = "单条件查询", notes = "只需要传入关键字，会匹配事故详情的所有的数据，找出能匹配上的")
//    @GetMapping("singleSelect")
//    public Result<List<Reason>> singleSelect(String keyword) {
//        Example record = Example.builder(Reason.class)
//                .where(WeekendSqls.<Reason>custom()
//                        .orLike(Reason::getReaid, "%" + keyword + "%")
//                        .orLike(Reason::getReaname, "%" + keyword + "%")
//                        .orLike(Reason::getReahow, "%" + keyword + "%")
//                )
//                .build();
//
//        List<Reason> list = reasonMapper.selectByExample(record);
//        return Result.createBySuccess(list);
//    }

    @ApiOperation(value = "查询所有原因")
    @GetMapping("all")
    public Result<List<Reason>> all() {
        List<Reason> list = reasonMapper.selectAll();
        return Result.createBySuccess(list);
    }


}