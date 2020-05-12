package com.coding.controller;


import com.coding.common.Const;
import com.coding.domain.Airdetail;
import com.coding.domain.Reason;
import com.coding.domain.Type;
import com.coding.mapper.AirdetailMapper;
import com.coding.mapper.ReasonMapper;
import com.coding.mapper.TypeMapper;
import com.coding.service.ManagerService;
import com.guanweiming.common.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.WeekendSqls;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j //lombok的一个注解，log需要使用
@Api(tags = "事故详情接口")
@AllArgsConstructor
@RestController
@RequestMapping(Const.API + "airdetail")
public class AirdetailController {
    private final ManagerService managerService;
    private final AirdetailMapper airdetailMapper;
    private final TypeMapper typeMapper;
    private final ReasonMapper reasonMapper;

    @ApiOperation("添加事故详情")
    @PostMapping("add")
    public Result<String> addAirdetail(String airname, String airtype, String airwhen, String airwhere, String airwhy,String airhow,String airimg) {
        if(!checkTypeExist(airtype)){
            return Result.createByErrorMessage("事故类型不存在");
        }
        if(!checkReasonExist(airwhy)){
            return Result.createByErrorMessage("事故原因不存在");
        }
        Airdetail param=new Airdetail();
        param.setAirid(UUID.randomUUID().toString());
        param.setAirname(airname);
        param.setAirtype(airtype);
        param.setAirwhen(airwhen);
        param.setAirwhere(airwhere);
        param.setAirwhy(airwhy);
        param.setAirhow(airhow);
        param.setAirimg(airimg);
        airdetailMapper.insertSelective(param);
        return Result.createBySuccess();
    }

    private boolean checkReasonExist(String airwhy) {
        Reason record=new Reason();
        record.setReaname(airwhy);
        return reasonMapper.selectCount(record)>0;
    }

    private boolean checkTypeExist(String airtype) {
        Type record=new Type();
        record.setTypename(airtype);
        return typeMapper.selectCount(record)>0;
    }

    @ApiOperation("更新事故详情")
    @PostMapping("update")
    public Result<String> updateAirdetail(Airdetail param) {
        airdetailMapper.updateByPrimaryKeySelective(param);
        return Result.createBySuccess();
    }

    @ApiOperation("删除事故详情")
    @PostMapping("delete")
    public Result<String> deleteAirdetail(@RequestParam String airid) {
        airdetailMapper.deleteByPrimaryKey(airid);
        return Result.createBySuccess();
    }


    @ApiOperation("按事件类型查询")
    @GetMapping("airtypeselect")
    public Result<List<Airdetail>> airtypeselect(String airtype) {
        Example record = Example.builder(Airdetail.class)
                .where(WeekendSqls.<Airdetail>custom()
                        .andLike(Airdetail::getAirtype, "%" + airtype + "%")
                )
                .build();

        List<Airdetail> list=airdetailMapper.selectByExample(record);
        return Result.createBySuccess(list);
    }

    @ApiOperation("按事件原因查询")
    @GetMapping("airwhyselect")
    public Result<List<Airdetail>> airwhyselect( String airwhy) {
        Example record = Example.builder(Airdetail.class)
                .where(WeekendSqls.<Airdetail>custom()
                        .andLike(Airdetail::getAirwhy, "%" + airwhy + "%")
                )
                .build();

        List<Airdetail> list=airdetailMapper.selectByExample(record);
        return Result.createBySuccess(list);
    }

    @ApiOperation("多条件查询")
    @GetMapping("allselect")
    public Result<List<Airdetail>> airwhyselect(String airname, String airtype, String airwhen, String airwhere, String airwhy) {

        List<Airdetail>list =new ArrayList<>();
        List<Airdetail>list1=new ArrayList<>();
        List<Airdetail>list2=new ArrayList<>();
        List<Airdetail>list3=new ArrayList<>();
        List<Airdetail>list4=new ArrayList<>();

        if(!StringUtils.isBlank(airname)){
            Example record = Example.builder(Airdetail.class)
                    .where(WeekendSqls.<Airdetail>custom()
                            .andLike(Airdetail::getAirname, "%" + airname + "%")
                    )
                    .build();
            list=airdetailMapper.selectByExample(record);
        }else {
            list=airdetailMapper.selectAll();
        }
        if(!StringUtils.isBlank(airtype)){
            for (int i = 0; i < list.size(); i++){
                String type =list.get(i).getAirtype();
                if(type.contains(airtype)){
                    list1.add(list.get(i));
                }
            }
        } else {
            list1.addAll(list);
        }
        if(!StringUtils.isBlank(airwhen)){
            for (int i = 0; i < list1.size(); i++){
                String when =list1.get(i).getAirwhen();
                if(when.contains(airwhen)){
                    list2.add(list1.get(i));
                }
            }
        } else {
            list2.addAll(list1);
        }
        if(!StringUtils.isBlank(airwhere)){
            for (int i = 0; i < list2.size(); i++){
                String where =list2.get(i).getAirwhere();
                if(where.contains(airwhere)){
                    list3.add(list2.get(i));
                }
            }
        } else {
            list3.addAll(list2);
        }
        if(!StringUtils.isBlank(airwhy)){
            for (int i = 0; i < list3.size(); i++){
                String why =list3.get(i).getAirwhy();
                if(why.contains(airwhy)){
                    list4.add(list3.get(i));
                }
            }
        } else {
            list4.addAll(list3);
        }
        return Result.createBySuccess(list4);
    }

    @ApiOperation(value = "单条件查询", notes = "只需要传入关键字，会匹配事故详情的所有的数据，找出能匹配上的")
    @GetMapping("singleSelect")
    public Result<List<Airdetail>> singleSelect(String keyword) {
        if(StringUtils.isBlank(keyword)){
            return Result.createBySuccess(airdetailMapper.selectAll());
        }
        Example record = Example.builder(Airdetail.class)
                .where(WeekendSqls.<Airdetail>custom()
                        .orLike(Airdetail::getAirhow, "%" + keyword + "%")
                        .orLike(Airdetail::getAirname, "%" + keyword + "%")
                        .orLike(Airdetail::getAirtype, "%" + keyword + "%")
                        .orLike(Airdetail::getAirwhen, "%" + keyword + "%")
                        .orLike(Airdetail::getAirwhere, "%" + keyword + "%")
                        .orLike(Airdetail::getAirwhy, "%" + keyword + "%")
                )
                .build();

        List<Airdetail> list=airdetailMapper.selectByExample(record);
        return Result.createBySuccess(list);
    }

    @ApiOperation("事故详情")
    @PostMapping("airdetailDetail")
    public Result<Airdetail> AirdetailDetail(String airid) {
        Airdetail airdetail = managerService.Detail(airid);
        if (airdetail == null) {
            return Result.createByErrorMessage("找不到");
        }
        return Result.createBySuccess(airdetail);

    }
}
