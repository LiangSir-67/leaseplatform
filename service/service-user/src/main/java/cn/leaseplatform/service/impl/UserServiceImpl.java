package cn.leaseplatform.service.impl;

import cn.hutool.crypto.SecureUtil;
import cn.leaseplatform.commonutils.JwtUtils;
import cn.leaseplatform.entity.User;
import cn.leaseplatform.utils.UserJwtTokenUtils;
import cn.leaseplatform.vo.UserLoginVo;
import cn.leaseplatform.vo.UserRegisterVo;
import cn.leaseplatform.mapper.UserMapper;
import cn.leaseplatform.service.UserService;
import cn.leaseplatform.servicebase.exceptionhandler.LPException;
import com.alibaba.csp.sentinel.util.StringUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 此处留名QCS
 * @since 2021-03-22
 */
@Service
public class  UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    private UserMapper userMapper;
    /**
     * 用户注册
     * @param userRegisterVo
     */
    @Override
    public void register(UserRegisterVo userRegisterVo) {
        String username = userRegisterVo.getUsername();
        String telephone = userRegisterVo.getTelephone();
        String code = userRegisterVo.getCode();
        String password = userRegisterVo.getPassword();

        //参数校验
        if (StringUtil.isEmpty(username) || StringUtil.isEmpty(telephone) || StringUtil.isEmpty(password) || StringUtil.isEmpty(code)){
            throw new LPException(100,"参数不能为空！");
        }

        //验证码校验
        String telephoneCode = redisTemplate.opsForValue().get(telephone);
        if (!code.equals(telephoneCode)){
            throw new LPException(100,"验证码错误！");
        }

        // 判断数据库中是否存在相同的手机号码
        Integer selectCount = baseMapper.selectCount(new QueryWrapper<User>().eq("telephone", telephone));
        if (selectCount.intValue() > 0){
            throw new LPException(100,"该手机号码已被注册！");
        }

        //添加信息到数据库
        User user = new User();
        user.setUsername(username);
        user.setTelephone(telephone);
        user.setPassword(SecureUtil.md5(password));
        this.save(user);
    }

    /**
     * 用户登录
     * @param userLoginVo
     * @return
     */
    @Override
    public String login(UserLoginVo userLoginVo) {
        String username = userLoginVo.getUsername();
        String password = userLoginVo.getPassword();

        //参数校验
        if (StringUtil.isEmpty(username) || StringUtil.isEmpty(password)){
            throw new LPException(100,"用户名和密码都不能为空！");
        }

        //获取用户
        User user = baseMapper.selectOne(new QueryWrapper<User>().eq("username",username));

        //校验用户是否存在
        if (null == user){
            throw new LPException(100,"该用户不存在！");
        }

        //校验密码是否正确
        if (!SecureUtil.md5(password).equals(user.getPassword())){
            throw new LPException(100,"密码不正确，请重新输入！");
        }

        //使用JWT生成token字符串
        //String jwtToken = JwtUtils.getJwtToken(String.valueOf(user.getUserId()), user.getUsername());
        Map<String,String> payload = new HashMap<>();
        payload.put("userId", String.valueOf(user.getUserId()));
        payload.put("username",user.getUsername());
        String jwtToken = UserJwtTokenUtils.getToken(payload);
        return jwtToken;
    }

    /**
     * 获取用户登录信息
     * @param userId
     * @return
     */
    @Override
    public UserLoginVo getLoginInfo(String userId) {
        User user = baseMapper.selectById(userId);
        UserLoginVo userLoginVo = new UserLoginVo();
        BeanUtils.copyProperties(user,userLoginVo);
        return userLoginVo;
    }

    /**
     * 修改用户头像
     * @param userId
     * @param url
     */
    @Override
    public Integer updateUserHeadPortrait(String userId, String url) {
        User user = baseMapper.selectById(userId);
        user.setUrl(url);
        return userMapper.updateById(user);
    }

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    @Override
    public Integer updateUserInfo(User user) {
        User userResult = baseMapper.selectById(user.getUserId());
        userResult.setSex(user.getSex());
        userResult.setIdnumber(user.getIdnumber());
        userResult.setAddress(user.getAddress());
        return userMapper.updateById(userResult);
    }
}
