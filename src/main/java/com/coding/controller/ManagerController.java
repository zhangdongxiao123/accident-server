package com.coding.controller;

import com.coding.common.Const;
import com.coding.domain.Manager;
import com.coding.mapper.ManagerMapper;
import com.coding.pojo.param.ManagerRegisterParam;
import com.coding.service.MinIOFileService;
import com.coding.service.ManagerService;
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
@Api(tags = "管理员接口")
@AllArgsConstructor
@RequestMapping(Const.API + "manager")
@RestController
public class ManagerController {
    private final ManagerService managerService;
    private final MinIOFileService fileService;
    private final ManagerMapper managerMapper;



    //登录
    @ApiOperation("登陆接口，返回管理员数据")
    @PostMapping("mLogin")
    public Result<Manager> login(
            @RequestParam String manacct,
            @RequestParam String manpwd) {
        //调用userService 的方法完成登录逻辑
        if (StringUtils.isBlank(manacct)) {
            return Result.createByErrorMessage("管理员账号不能为空");
        }
        Manager manager = managerService.findManagerByName(manacct);
            if (manager == null) {
            return Result.createByErrorMessage("没有该管理员的信息");
        }
        if (!Objects.equals(manager.getManpwd(), manpwd)) {
            return Result.createByErrorMessage("管理员密码错误");
        }
        return Result.createBySuccess(manager);
    }

    @ApiOperation("注册管理员接口")
    @PostMapping("mRegister")
    public Result<String> register(@Validated ManagerRegisterParam param) {
        log.info("param:{}", JsonUtil.toJson(param));
        //调用ManagerService 的方法完成注册逻辑，如果用户名已经存在，就不能注册
        Manager manager = new Manager();
        manager.setManacct(param.getManacct());
        if (managerMapper.selectCount(manager) > 0) {
            return Result.createByErrorMessage("用户已存在");
        }
        manager.setManpwd(param.getManpwd());
        managerMapper.insertSelective(manager);
        return Result.createBySuccess();
    }

    @ApiOperation("更新管理员接口")
    @PostMapping("updateManagerInfo")
    public Result<String> updateManagerInfo(@Validated Manager manager) {
        return managerService.updateManagerInfo(manager);
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
