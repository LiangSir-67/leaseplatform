package cn.leaseplatform.service.impl;

import cn.leaseplatform.entity.PersonalOrders;
import cn.leaseplatform.mapper.PersonalOrdersMapper;
import cn.leaseplatform.service.PersonalOrdersService;
import cn.leaseplatform.vo.UserPersonalOrderVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 梁歪歪
 * @since 2021-03-31
 */
@Service
public class PersonalOrdersServiceImpl extends ServiceImpl<PersonalOrdersMapper, PersonalOrders> implements PersonalOrdersService {

    @Override
    public Page<UserPersonalOrderVo> getUserPersonalOrders(Page<UserPersonalOrderVo> page, Integer userId) {
        return page.setRecords((List<UserPersonalOrderVo>) this.baseMapper.getUserPersonalOrders(page,userId));
    }
}
