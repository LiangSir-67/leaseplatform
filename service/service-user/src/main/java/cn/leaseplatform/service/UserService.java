package cn.leaseplatform.service;

import cn.leaseplatform.entity.User;
import cn.leaseplatform.entity.UserLoginVo;
import cn.leaseplatform.entity.UserRegisterVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 此处留名QCS
 * @since 2021-03-22
 */
public interface UserService extends IService<User> {
    /**
     * 用户注册
     * @param userRegisterVo
     */
    void register(UserRegisterVo userRegisterVo);

    /**
     * 用户登录
     * @param userLoginVo
     * @return
     */
    String login(UserLoginVo userLoginVo);

    /**
     * 获取用户登录信息
     * @param userId
     * @return
     */
    UserLoginVo getLoginInfo(String userId);
}
