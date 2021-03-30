package cn.leaseplatform.controller;


import cn.leaseplatform.commonutils.R;
import cn.leaseplatform.entity.User;
import cn.leaseplatform.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 此处留名QCS
 * @since 2021-03-22
 */
@Api(tags = "用户服务")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

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

        int result = userMapper.insert(user);
        System.out.println("影响的行数："+result);
        System.out.println(user);
    }

    @ApiOperation(value = "测试-获取用户列表")
    @GetMapping("/getUser")
    public R testGetUserList(@RequestParam(defaultValue = "1") Integer currentPage){
        Page<User> page = new Page<>(currentPage, 10);
        IPage<User> userIPage = userMapper.selectPage(page, new QueryWrapper<User>().orderByDesc("create_time"));
        // List<User> users = userMapper.selectList(null);
        // users.forEach(System.out::println);
        return R.ok().data("userInfo",userIPage);
    }

    @ApiOperation(value = "测试-通过id修改用户信息")
    @GetMapping("/updateById")
    public void testUpdateById(){
        User user = new User();
        user.setUserId(109);
        user.setUsername("锐雯");
        user.setSex("女");
        user.setPassword("23dsf4dsf");
        user.setTelephone("15540432343");
        user.setUrl("https://gitee.com/liangsir-67/imagerepo/raw/master/img/20210303215459.png");
        user.setAddress("四川成都");
        int result = userMapper.updateById(user);
        System.out.println("被影响的行数："+result);
    }
}