package com.coding.controller;

import com.coding.common.Const;
import com.coding.domain.Manager;
import com.coding.mapper.ManagerMapper;
import com.coding.pojo.param.UserRegisterParam;
import com.coding.service.MinIOFileService;
import com.coding.service.UserService;
import com.guanweiming.common.utils.JsonUtil;
import com.guanweiming.common.utils.Result;
import io.minio.errors.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;


@Slf4j
@Api(tags = "用户接口")
@AllArgsConstructor
@RequestMapping(Const.API + "user")
@RestController
public class UserController {
    private final UserService userService;
    private final MinIOFileService fileService;
    private final ManagerMapper managerMapper;



    //登录
    @ApiOperation("登陆接口，返回用户数据")
    @PostMapping("login")
    public Result<Manager> login(
            @RequestParam String username,
            @RequestParam String password) {
        //调用userService 的方法完成登录逻辑
        if (StringUtils.isBlank(username)) {
            return Result.createByErrorMessage("登录失败");
        }
        Manager user = userService.findUserByName(username);
            if (user == null) {
            return Result.createByErrorMessage("登录失败");
        }
        if (!Objects.equals(user.getManpwd(), password)) {
            return Result.createByErrorMessage("登录失败");
        }
        return Result.createBySuccess(user);
    }

    @ApiOperation("注册用户接口")
    @PostMapping("register")
    public Result<String> register(@Validated UserRegisterParam param) {
        log.info("param:{}", JsonUtil.toJson(param));
        //调用userService 的方法完成注册逻辑，如果用户名已经存在，就不能注册
        Manager manager = new Manager();
        manager.setManacct(param.getUsername());
        if (managerMapper.selectCount(manager) > 0) {
            return Result.createByErrorMessage("用户已存在");
        }
        manager.setManpwd(param.getPassword());
        managerMapper.insertSelective(manager);
        return Result.createBySuccess();
    }

    @ApiOperation("更新用户接口")
    @PostMapping("updateUserInfo")
    public Result<String> updateUserInfo(@Validated Manager manager) {
        return userService.updateUserInfo(manager);
    }


    @ApiOperation("上传图片")
    @PostMapping("upload")
    public Result<String> userInfo(MultipartFile file) throws IOException, NoSuchAlgorithmException, RegionConflictException, InvalidKeyException, InvalidPortException, InvalidResponseException, ErrorResponseException, XmlParserException, InvalidBucketNameException, InsufficientDataException, InvalidEndpointException, InternalException {
        String url = fileService.upload(file.getInputStream(), file.getOriginalFilename());
        Result<String> bySuccess = Result.createBySuccess(url);
        log.info("url:{}", bySuccess);
        return bySuccess;
    }


}
