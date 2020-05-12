package com.coding.service;

import com.coding.domain.*;
import com.coding.mapper.*;
import com.guanweiming.common.utils.Result;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@AllArgsConstructor
@Service
public class ManagerService {
    private final AirdetailMapper airdetailMapper;
    private final ManagerMapper managerMapper;


    public Airdetail Detail(String airid){
        return airdetailMapper.selectByPrimaryKey(airid);
    }

    public Result<String> updateManagerInfo(Manager manager) {
        Manager record = new Manager();
        record.setManacct(manager.getManacct());
        record = managerMapper.selectOne(record);
        if (record == null) {
            return Result.createByErrorMessage("管理员查询失败");
        }
        record.setManpwd(manager.getManpwd());
        managerMapper.updateByPrimaryKeySelective(manager);
        return Result.createBySuccess();
    }

    public Manager findManagerByName(String username) {
        Manager record=new Manager();
        record.setManacct(username);
        return managerMapper.selectOne(record);

    }
}
