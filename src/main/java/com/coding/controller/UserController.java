package com.coding.controller;

import com.coding.common.Const;
import com.coding.domain.Manager;
import com.coding.domain.User;
import com.coding.mapper.ManagerMapper;
import com.coding.mapper.UserMapper;
import com.coding.pojo.param.ManagerRegisterParam;
import com.coding.pojo.param.UserRegisterParam;
import com.coding.service.MinIOFileService;
import com.coding.service.ManagerService;
import com.coding.service.UserService;
import com.guanweiming.common.utils.JsonUtil;
import com.guanweiming.common.utils.Result;
import io.minio.errors.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Slf4j
@Api(tags = "普通用户接口")
@AllArgsConstructor
@RequestMapping(Const.API + "user")
@RestController
public class UserController {
    private final ManagerService managerService;
    private final UserService userService;
    private final ManagerMapper managerMapper;
    private final UserMapper userMapper;

    //登录
    @ApiOperation("登陆接口，返回普通用户数据")
    @PostMapping("uLogin")
    public Result<User> login(
            @RequestParam String useracct,
            @RequestParam String userpwd) {
        //调用userService 的方法完成登录逻辑
        if (StringUtils.isBlank(useracct)) {
            return Result.createByErrorMessage("用户名不能为空");
        }
        User user = userService.findUserByName(useracct);
        if (user == null) {
            return Result.createByErrorMessage("没有该用户");
        }
        if (!Objects.equals(user.getUserpwd(), userpwd)) {
            return Result.createByErrorMessage("普通用户密码错误");
        }
        return Result.createBySuccess(user);
    }

    @ApiOperation("注册普通用户接口")
    @PostMapping("uRegister")
    public Result<String> register(@Validated UserRegisterParam param) {
        log.info("param:{}", JsonUtil.toJson(param));
        //调用userService 的方法完成注册逻辑，如果用户名已经存在，就不能注册
        User user=new User();
        user.setUseracct(param.getUseracct());
        if (userMapper.selectCount(user) > 0) {
            return Result.createByErrorMessage("用户已存在");
        }
        user.setUserpwd(param.getUserpwd());
        userMapper.insertSelective(user);
        return Result.createBySuccess();
    }

    @ApiOperation("更新普通用户接口")
    @PostMapping("updateUserInfo")
    public Result<String> updateUserInfo(@Validated User user) {
        return userService.updateUserInfo(user);
    }
}



