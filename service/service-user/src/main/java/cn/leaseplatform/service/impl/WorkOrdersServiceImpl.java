package cn.leaseplatform.service.impl;

import cn.leaseplatform.entity.User;
import cn.leaseplatform.entity.WorkOrders;
import cn.leaseplatform.mapper.WorkOrdersMapper;
import cn.leaseplatform.service.WorkOrdersService;
import cn.leaseplatform.vo.UserWorkOrdersVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 梁歪歪
 * @since 2021-03-31
 */
@Service
public class WorkOrdersServiceImpl extends ServiceImpl<WorkOrdersMapper, WorkOrders> implements WorkOrdersService {

    @Override
    public Page<UserWorkOrdersVo> getUserWorkOrders(Page<UserWorkOrdersVo> page) {
        return page.setRecords(this.baseMapper.getUserWorkOrders(page));
    }

    @Override
    public Page<UserWorkOrdersVo> pageQuery(Page<UserWorkOrdersVo> page) {
        QueryWrapper<UserWorkOrdersVo> userWorkOrdersVoQueryWrapper = new QueryWrapper<>();
        userWorkOrdersVoQueryWrapper.orderByAsc("sort");

        return null;
    }
}
