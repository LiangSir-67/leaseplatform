package cn.leaseplatform.service.impl;

import cn.leaseplatform.commonutils.JwtUtils;
import cn.leaseplatform.entity.LoginVo;
import cn.leaseplatform.entity.ManfactureVo;
import cn.leaseplatform.entity.Manufacturers;
import cn.leaseplatform.entity.RegisterVo;
import cn.leaseplatform.mapper.ManufacturersMapper;
import cn.leaseplatform.service.ManufacturersService;
import cn.leaseplatform.servicebase.exceptionhandler.LPException;
import cn.leaseplatform.utils.MD5;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 此处留名QCS
 * @since 2021-03-29
 */
@Service
public class ManufacturersServiceImpl extends ServiceImpl<ManufacturersMapper, Manufacturers> implements ManufacturersService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private ManufacturersMapper manufacturersMapper;

    //登录的方法
    @Override
    public String login(LoginVo loginVo) {
        //获取登录账号和密码
        String adminname = loginVo.getBusinessesName();
        String password = loginVo.getPassword();

        //账号号和密码非空判断
        if (StringUtils.isEmpty(adminname) || StringUtils.isEmpty(password)) {
            throw new LPException(201, "登录异常");
        }


        Manufacturers manufacturers = baseMapper.selectOne(new QueryWrapper<Manufacturers>().eq("Businesses_name", adminname));
        if (null == manufacturers) {
            throw new LPException(201, "error");
        }
        //判断密码
        //因为存储到数据库密码肯定加密的
        //把输入的密码进行加密，再和数据库密码进行比较
        //加密方式 MD5
        if(!MD5.encrypt(password).equals(manufacturers.getPassword())) {
            throw new LPException(20001,"登录失败");
        }


        //登录成功
        //生成token字符串，使用jwt工具类
        String jwtToken = JwtUtils.getJwtToken(String.valueOf(manufacturers.getManufacturerId()), manufacturers.getBusinessesName());
        return jwtToken;
    }

    //注册的方法
    @Override
    public void register(RegisterVo registerVo) {
        //获取注册信息，进行校验
        String nickname = registerVo.getAdminname();
        String password = registerVo.getPassword();
        String phone =registerVo.getPhone();
        String code = registerVo.getCode();
        if (StringUtils.isEmpty(nickname) || StringUtils.isEmpty(password) || StringUtils.isEmpty(phone) || StringUtils.isEmpty(code)) {
            throw new LPException(201, "error");
        }
        //判断验证码
        //获取redis验证码
        String redisCode = redisTemplate.opsForValue().get(phone);
        if(!code.equals(redisCode)) {
            throw new LPException(100,"注册失败");
        }

        //判断手机号是否重复，表里面存在相同手机号不进行添加
        QueryWrapper<Manufacturers> wrapper = new QueryWrapper<>();
        wrapper.eq("phone",phone);
        Integer count = baseMapper.selectCount(wrapper);
        if(count > 0) {
            throw new LPException(100,"注册失败");
        }


        Manufacturers manufacturers = new Manufacturers();
        manufacturers.setBusinessesName(nickname);
        manufacturers.setPassword(MD5.encrypt(password));
        manufacturers.setPhone(phone);

        this.save(manufacturers);


    }

    @Override
    public LoginVo getLoginInfo(String Id) {
        Manufacturers manufacturers = baseMapper.selectById(Id);
        LoginVo loginVo = new LoginVo();
        BeanUtils.copyProperties(manufacturers, loginVo);
        return loginVo;
    }

    @Override
    public void editManPicture(String url,Long BusiId){

        manufacturersMapper.editManPicture(url,BusiId);

    }

    @Override
    public void editManFctureInfo(ManfactureVo manfactureVo, Long BusiId){
        manufacturersMapper.editManFctureInfo(manfactureVo,BusiId);
    }




}
