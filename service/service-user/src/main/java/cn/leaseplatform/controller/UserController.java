package cn.leaseplatform.controller;


import cn.leaseplatform.commonutils.ExceptionUtil;
import cn.leaseplatform.commonutils.R;
import cn.leaseplatform.entity.User;
import cn.leaseplatform.utils.UserJwtTokenUtils;
import cn.leaseplatform.vo.UserLoginVo;
import cn.leaseplatform.vo.UserRegisterVo;
import cn.leaseplatform.mapper.UserMapper;
import cn.leaseplatform.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 梁歪歪
 * @since 2021-03-22
 */
@Api(tags = "用户服务")
@RestController
@RequestMapping("/service-user/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @ApiOperation(value = "个人账户注册")
    @PostMapping("/userRegister")
    public R userRegister(@RequestBody UserRegisterVo userRegisterVo) {
        try {
            userService.register(userRegisterVo);
            return R.ok().message("注册成功！");
        } catch (Exception e) {
            e.printStackTrace();
            log.error(ExceptionUtil.getMessage(e));
            return R.error().message("注册失败！");
        }
    }

    @ApiOperation(value = "个人账户登录")
    @PostMapping("/userLogin")
    public R userLogin(@RequestBody UserLoginVo userLoginVo, HttpServletResponse httpServletResponse) {
        String token = null;
        try {
            token = userService.login(userLoginVo);
            return R.ok().data("token", token).message("登录成功！");
        } catch (Exception e) {
            e.printStackTrace();
            log.error(ExceptionUtil.getMessage(e));
            return R.error().message("登录失败！");
        }
    }

    @ApiOperation(value = "根据token获取登录信息")
    @GetMapping("/auth/getLoginInfo")
    public R getUserLoginInfo(HttpServletRequest request) {
        // 获取请求头中的token
        try {
            String userId = UserJwtTokenUtils.getInfoForToken(request);
            System.out.println("token中的userId==" + userId);
            UserLoginVo userLoginVo = userService.getLoginInfo(userId);
            return R.ok().data("user", userLoginVo.getUsername());
        } catch (Exception e) {
            e.printStackTrace();
            log.error(ExceptionUtil.getMessage(e));
            return R.error().message("获取用户登录信息失败！");
        }
    }

    @ApiOperation(value = "获取当前用户信息")
    @GetMapping("/getUserInfo")
    public R getUserInfo(HttpServletRequest request) {
        try {
            String userId = UserJwtTokenUtils.getInfoForToken(request);
            User user = userMapper.selectById(userId);
            return R.ok().message("获取成功！").data("user", user);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(ExceptionUtil.getMessage(e));
        }
        return R.error().message("获取失败！");


    }

    @ApiOperation(value = "修改用户头像")
    @PostMapping("/updateUserHeadPortrait")
    public R updateUserHeadPortrait(@RequestParam String userId, @RequestParam String url) {
        try {
            Integer result = userService.updateUserHeadPortrait(userId, url);
            if (result > 0) {
                return R.ok().message("修改成功！");
            } else {
                return R.error().message("修改失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(ExceptionUtil.getMessage(e));
        }
        return R.error().message("修改失败！");
    }

    @ApiOperation(value = "用户修改信息")
    @PostMapping("/updateUserInfo")
    public R updateUserInfo(@RequestBody User user) {
        try {
            Integer result = userService.updateUserInfo(user);
            return R.ok().message("修改成功！").data("status", 1);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(ExceptionUtil.getMessage(e));
        }
        return R.error().message("修改失败！");
    }
}
