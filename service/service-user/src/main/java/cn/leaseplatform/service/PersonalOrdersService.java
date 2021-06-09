package cn.leaseplatform.service;

import cn.leaseplatform.entity.PersonalOrders;
import cn.leaseplatform.vo.UserPersonalOrderVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 梁歪歪
 * @since 2021-03-31
 */
public interface PersonalOrdersService extends IService<PersonalOrders> {

    Page<UserPersonalOrderVo> getUserPersonalOrders(Page<UserPersonalOrderVo> page, Integer userId);
}
