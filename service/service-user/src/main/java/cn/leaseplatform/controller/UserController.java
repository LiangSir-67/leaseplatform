package cn.leaseplatform.controller;

import cn.hutool.crypto.SecureUtil;
import cn.leaseplatform.commonutils.JwtUtils;
import cn.leaseplatform.commonutils.R;
import cn.leaseplatform.entity.User;
import cn.leaseplatform.entity.UserLoginVo;
import cn.leaseplatform.entity.UserRegisterVo;
import cn.leaseplatform.mapper.UserMapper;
import cn.leaseplatform.service.UserService;
import cn.leaseplatform.commonutils.ExceptionUtil;
import cn.leaseplatform.commonutils.JwtUtils;
import cn.leaseplatform.commonutils.R;
import cn.leaseplatform.entity.User;
import cn.leaseplatform.utils.UserJwtTokenUtils;
import cn.leaseplatform.vo.UserLoginVo;
import cn.leaseplatform.vo.UserRegisterVo;
import cn.leaseplatform.mapper.UserMapper;
import cn.leaseplatform.service.UserService;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 梁歪歪
 * @since 2021-03-22
 */
@Api(tags = "用户服务")
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @ApiOperation(value = "测试-添加")
    @GetMapping("/insert")
    public void testInsert(){
        User user = new User();
        user.setRealname("疾风剑豪");
        user.setUsername("亚索");
        user.setSex("男");
        user.setPassword("234dsf");
        user.setTelephone("15500432343");
        user.setUrl("https://gitee.com/liangsir-67/imagerepo/raw/master/img/20210303215459.png");
        user.setAddress("四川成都");
    @ApiOperation(value = "个人账户注册")
    @PostMapping("/userRegister")
    public R userRegister(@RequestBody UserRegisterVo userRegisterVo){
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
    public R userLogin(@RequestBody UserLoginVo userLoginVo, HttpServletResponse httpServletResponse){
        String token = null;
        try {
            token = userService.login(userLoginVo);
            return R.ok().data("token",token).message("登录成功！");
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
        //String token = request.getHeader("token");
        //DecodedJWT verify = UserJwtTokenUtils.verify(token);
        try {
            //String userId = verify.getClaim("userId").asString();
            String userId = UserJwtTokenUtils.getInfoForToken(request);
            System.out.println("token中的userId=="+userId);
            UserLoginVo userLoginVo = userService.getLoginInfo(userId);
            //User user = userMapper.selectById(userId);
            return R.ok().data("user", userLoginVo.getUsername());
        } catch (Exception e) {
            e.printStackTrace();
            log.error(ExceptionUtil.getMessage(e));
            return R.error().message("获取用户登录信息失败！");
        }
    }

    @ApiOperation(value = "修改用户头像")
    @PostMapping("/updateUserHeadPortrait")
    public R updateUserHeadPortrait(@RequestParam String userId, @RequestParam String url){
        try {
            Integer result = userService.updateUserHeadPortrait(userId, url);
            return R.ok().message("修改成功！");
        } catch (Exception e) {
            e.printStackTrace();
            log.error(ExceptionUtil.getMessage(e));
            return R.error().message("修改失败！");
        }
    }

    @ApiOperation(value = "用户修改信息")
    @PostMapping("/updateUserInfo")
    public R updateUserInfo(@RequestParam String userId,@RequestParam String address){
        try {
            Integer result = userService.updateUserInfo(userId, address);
            return R.ok().message("修改成功！");
        } catch (Exception e) {
            e.printStackTrace();
            log.error(ExceptionUtil.getMessage(e));
            return R.error().message("修改失败！");
        }
    }


    @ApiOperation(value = "个人账户注册")
    @PostMapping("/userRegister")
    public R userRegister(@RequestBody UserRegisterVo userRegisterVo){
        try {
            userService.register(userRegisterVo);
            return R.ok().message("注册成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return R.error().message("注册失败！");
        }
    }

    @ApiOperation(value = "个人账户登录")
    @PostMapping("/userLogin")
    public R userLogin(@RequestBody UserLoginVo userLoginVo){
        String token = null;
        try {
            token = userService.login(userLoginVo);
            return R.ok().data("token",token).message("登录成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return R.error().message("登录失败！");
        }

    }

    @ApiOperation(value = "根据token获取登录信息")
    @GetMapping("/auth/getLoginInfo")
    public R getUserLoginInfo(HttpServletRequest request) {
        try {
            String userId = JwtUtils.getMemberIdByJwtToken(request);
            UserLoginVo userLoginVo = userService.getLoginInfo(userId);
            return R.ok().data("item", userLoginVo);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error().message("获取用户登录信息失败！");
        }
    }
}
