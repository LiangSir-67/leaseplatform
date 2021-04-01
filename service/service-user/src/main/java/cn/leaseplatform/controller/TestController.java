package cn.leaseplatform.controller;

import cn.leaseplatform.commonutils.R;
import cn.leaseplatform.entity.User;
import cn.leaseplatform.mapper.UserMapper;
import cn.leaseplatform.service.UserService;
import cn.leaseplatform.servicebase.exceptionhandler.LPException;
import cn.leaseplatform.utils.UserJwtTokenUtils;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "测试")
@RestController
public class TestController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @ApiOperation(value = "测试-添加")
    @GetMapping("/insert")
    public void testInsert() {
        User user = new User();
        user.setRealname("疾风剑豪");
        user.setUsername("亚索");
        user.setSex("男");
        user.setPassword("234dsf");
        user.setTelephone("15500432343");
        user.setUrl("https://gitee.com/liangsir-67/imagerepo/raw/master/img/20210303215459.png");
        user.setAddress("四川成都");

        int result = userMapper.insert(user);
        System.out.println("影响的行数：" + result);
        System.out.println(user);
    }

    @ApiOperation(value = "测试-获取用户列表")
    @GetMapping("/getUser")
    public R testGetUserList(@RequestParam(defaultValue = "1") Integer currentPage) {
        Page<User> page = new Page<>(currentPage, 10);
        IPage<User> userIPage = userMapper.selectPage(page, new QueryWrapper<User>().orderByDesc("create_time"));
        return R.ok().data("userInfo", userIPage);
    }

    @ApiOperation(value = "测试-通过id修改用户信息")
    @GetMapping("/updateById")
    public void testUpdateById() {
        User user = new User();
        user.setUserId(109);
        user.setUsername("锐雯");
        user.setSex("女");
        user.setPassword("23dsf4dsf");
        user.setTelephone("15540432343");
        user.setUrl("https://gitee.com/liangsir-67/imagerepo/raw/master/img/20210303215459.png");
        user.setAddress("四川成都");
        int result = userMapper.updateById(user);
        System.out.println("被影响的行数：" + result);
    }

    @ApiOperation(value = "这是统一返回结果测试！")
    @GetMapping("/test/hello")
    public R hello() {
        Map<String, String> data = new HashMap<>();
        data.put("name", "亚索");
        data.put("age", "22");
        return R.ok().data("userinfo", data);
    }

    /**
     * 用来测试统一异常结果返回的
     *
     * @return
     */
    @ApiOperation(value = "这是异常测试！")
    @GetMapping("/test/exception")
    public R test() {
        try {
            // int i = 1/0;
            return R.error();
        } catch (Exception e) {
            // 执行自定义异常
            throw new LPException(100, "执行了自定义异常LPException");
        }
    }

    @ApiOperation(value = "用户token测试！")
    @PostMapping("/testToken")
    public Map<String, Object> testToken(String token) {
        Map<String, Object> map = new HashMap<>();
        try {
            // 验证令牌
            DecodedJWT verify = UserJwtTokenUtils.verify(token);
            // 执行业务  。。。。

            map.put("state", true);
            map.put("msg", "成功");
            return map;
        } catch (SignatureVerificationException e) {
            e.printStackTrace();
            map.put("msg", "无效签名");
        } catch (TokenExpiredException e) {
            e.printStackTrace();
            map.put("msg", "token过期");
        } catch (AlgorithmMismatchException e) {
            e.printStackTrace();
            map.put("msg", "算法不一致");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "无效签名");
        }
        map.put("state", false);
        return map;
    }

    @ApiOperation(value = "用户token测试-handler")
    @PostMapping("/testToken1")
    public Map<String, Object> testToken1(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        // 处理自己的业务逻辑
        String token = request.getHeader("token");
        DecodedJWT verify = UserJwtTokenUtils.verify(token);
        // 获取当前用户id和name
        String userId = verify.getClaim("userId").asString();
        String username = verify.getClaim("username").asString();

        System.out.println(userId+"=============="+username);

        map.put("state", true);
        map.put("msg", "成功");

        return map;
    }
}
