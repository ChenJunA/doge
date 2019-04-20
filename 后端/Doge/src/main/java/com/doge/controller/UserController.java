package com.doge.controller;

import com.doge.entity.User;
import com.doge.enums.StatusCode;
import com.doge.util.RespUtil;
import com.sun.org.apache.regexp.internal.RE;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Description: 用户相关操作
 * @Author: chenjun
 * @Date: 2019/4/12 10:53
 */
@RestController
public class UserController extends BaseController {
    /**
     * 登录
     *
     * @param user 用户信息
     * @return 状态信息
     */
    @ApiOperation("登录")
    @ApiImplicitParam(name = "user", value = "用户信息", required = true, dataType = "User")
    @PostMapping("/login")
    public RespUtil<User> login(@RequestBody User user) throws Exception {
        Map<String, Integer> map = userService.login(user);
        if (!map.isEmpty()) {
            if (map.get("statusCode") == StatusCode.USERNAME_ERROR.getCode()) {
                return RespUtil.fail(StatusCode.USERNAME_ERROR);
            } else if (map.get("statusCode") == StatusCode.PASSWORD_ERROR.getCode()) {
                return RespUtil.fail(StatusCode.PASSWORD_ERROR);
            }
        }
        return RespUtil.success();
    }

    /**
     * 登出
     *
     * @return 状态信息
     */
    @ApiOperation("登出")
    @PostMapping("/logout")
    public RespUtil<User> logout() throws Exception {
        userService.logout();
        return RespUtil.success();
    }


    /**
     * 注册
     *
     * @param user 用户信息
     * @return 状态信息
     */
    @ApiOperation("注册")
    @ApiImplicitParam(name = "user", value = "用户信息", required = true, dataType = "User")
    @PostMapping("/register")
    public RespUtil<User> register(@RequestBody User user) throws Exception {
        String email = user.getEmail();
        //邮箱已被注册
        if (userService.findByEmail(email) != null) {
            return RespUtil.fail(StatusCode.EMAIL_USEED);
        }
        userService.insertUser(user);
        return RespUtil.success();
    }

    /**
     * 激活
     *
     * @param userId 用户信息
     * @return 状态信息
     */
    @ApiOperation("激活")
    @ApiImplicitParam(name = "user", value = "用户信息", required = true, dataType = "User")
    @PutMapping("/active/{userId}")
    public RespUtil<User> active(@PathVariable Long userId) throws Exception {
        userService.active(userId);
        return RespUtil.success();
    }

    /**
     * 根据邮箱查找用户
     *
     * @param email 邮箱
     * @return 状态信息
     */
    @ApiOperation("根据邮箱查找用户")
    @ApiImplicitParam(name = "email", value = "邮箱", required = true, dataType = "String")
    @GetMapping("/findByEmail")
    public RespUtil<User> findByEmail(String email) throws Exception {
        User emailUser = userService.findByEmail(email);
        if (emailUser != null) {
            return RespUtil.fail(StatusCode.EMAIL_USEED);
        }
        return RespUtil.success();
    }

    /**
     * 封禁用户
     *
     * @param userId 用户ID
     * @return 状态信息
     */
    @ApiOperation("封禁用户")
    @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "Long")
    @PutMapping("/ban")
    public RespUtil<User> ban(@PathVariable Long userId) throws Exception {
        userService.ban(userId);
        return RespUtil.success();
    }
}
