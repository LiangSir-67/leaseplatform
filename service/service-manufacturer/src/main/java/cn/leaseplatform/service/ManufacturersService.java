package cn.leaseplatform.service;

import cn.leaseplatform.entity.*;
import com.baomidou.mybatisplus.extension.service.IService;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 此处留名QCS
 * @since 2021-03-29
 */
@Service
public interface ManufacturersService extends IService<Manufacturers> {

    public String login(LoginVo loginVo);

    public void register(RegisterVo registerVo);
    public LoginVo getLoginInfo(String Id);
    public void editManPicture(String url,Long BusiId);

    public void editManFctureInfo(ManfactureVo manfactureVo, Long BusiId);


}
