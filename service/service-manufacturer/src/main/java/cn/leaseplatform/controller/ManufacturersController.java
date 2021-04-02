package cn.leaseplatform.controller;


import cn.leaseplatform.commonutils.JwtUtils;
import cn.leaseplatform.commonutils.R;
import cn.leaseplatform.commonutils.TokenUtils;
import cn.leaseplatform.entity.LoginVo;
import cn.leaseplatform.entity.ManfactureVo;
import cn.leaseplatform.entity.Manufacturers;
import cn.leaseplatform.entity.RegisterVo;
import cn.leaseplatform.service.ManufacturersService;
import cn.leaseplatform.servicebase.exceptionhandler.LPException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
        } catch (LPException e) {
            e.printStackTrace();
            return R.error();
        }
    }
    @ApiOperation(value = "获取商家个人中心")
    @GetMapping("/germanfainfo")
    public R getManfaInfo(HttpServletRequest request){
        String ID = TokenUtils.getId(request);
        Long Id = Long.parseLong(ID);
        try{
            Manufacturers manufacturers =manufacturersService.getById(Id);
            return R.ok().data("manufacturers",manufacturers);
        }catch (LPException e){
            return R.error();
        }
    }
    @ApiOperation(value = "修改商户图片")
    @PutMapping("/editmanpic/{url}")
    public R editManPicture(HttpServletRequest request,@ApiParam(name = "url",value = "图片地址",required = true) @PathVariable String url){
        try {
            String ID = TokenUtils.getId(request);
            Long BusiId = Long.parseLong(ID);
            manufacturersService.editManPicture(url,BusiId);
            return R.ok();
        } catch (LPException e) {
            e.printStackTrace();
            return R.error();
        }
    }
    @ApiOperation(value = "修改商家信息")
    @PutMapping("/editmanfactureinfo")
    public R editManFacturerInfo(HttpServletRequest request, @RequestBody ManfactureVo manfactureVo){

        try {
            String ID = TokenUtils.getId(request);
            Long BusiId = Long.parseLong(ID);
            manufacturersService.editManFctureInfo(manfactureVo,BusiId);
            return R.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }

    }







}
