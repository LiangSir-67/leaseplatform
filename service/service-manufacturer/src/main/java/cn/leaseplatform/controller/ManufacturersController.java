package cn.leaseplatform.controller;


import cn.leaseplatform.commonutils.JwtUtils;
import cn.leaseplatform.commonutils.R;
import cn.leaseplatform.entity.LoginVo;
import cn.leaseplatform.entity.RegisterVo;
import cn.leaseplatform.service.ManufacturersService;
import cn.leaseplatform.servicebase.exceptionhandler.LPException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 此处留名QCS
 * @since 2021-03-29
 */
@RestController
@RequestMapping("/manufacturers")
@Api(tags = "商家服务")
public class ManufacturersController {

    @Autowired
    private ManufacturersService manufacturersService;

    @ApiOperation(value = "商家登录")
    @PostMapping("/login")
    public R login(@ApiParam(name = "loginvo", value = "登陆对象", required = true)
                   @RequestBody LoginVo loginVo) {
        try {
            String token = manufacturersService.login(loginVo);
            return R.ok().data("token", token);

        } catch (Exception e) {
            return R.error().message("用户名或者密码错误");
        }


    }


    @ApiOperation(value = "商家注册")
    @PostMapping("/register")
    public R register(@ApiParam(name = "registervo", value = "注册对象", required = true)
                      @RequestBody RegisterVo registerVo) {
        try{
            manufacturersService.register(registerVo);
            return R.ok();
        }catch (Exception e){
            return R.error().message("注册失败");

        }
    }

    @GetMapping("auth/getLoginInfo")
    @ApiOperation(value = "根据token获取登录信息")
    public R getLoginInfo(HttpServletRequest request) {
        try {
            String Id = JwtUtils.getMemberIdByJwtToken(request);
            System.out.println("用户id为");
            System.out.println(Id);
            LoginVo loginVo = manufacturersService.getLoginInfo(Id);
            return R.ok().data("item", loginVo);
        } catch (Exception e) {
            e.printStackTrace();
            //throw new LPException(201, "error");
            return R.error();
        }
    }

}
