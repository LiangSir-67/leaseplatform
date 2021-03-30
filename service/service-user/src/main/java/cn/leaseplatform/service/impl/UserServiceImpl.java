package cn.leaseplatform.service.impl;

import cn.leaseplatform.entity.User;
import cn.leaseplatform.mapper.UserMapper;
import cn.leaseplatform.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 此处留名QCS
 * @since 2021-03-22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
