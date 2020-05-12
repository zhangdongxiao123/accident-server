package com.coding.service;


import com.coding.domain.User;
import com.coding.mapper.AirdetailMapper;
import com.coding.mapper.UserMapper;
import com.guanweiming.common.utils.Result;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class UserService {
    private final UserMapper userMapper;


    public Result<String> updateUserInfo(User user) {
        User record = new User();
        record.setUseracct(user.getUseracct());
        record = userMapper.selectOne(record);
        if (record == null) {
            return Result.createByErrorMessage("用户查询失败");
        }
        record.setUserpwd(user.getUserpwd());
        userMapper.updateByPrimaryKeySelective(user);
        return Result.createBySuccess();
    }

    public User findUserByName(String username) {
        User record = new User();
        record.setUseracct(username);
        return userMapper.selectOne(record);

    }

}

