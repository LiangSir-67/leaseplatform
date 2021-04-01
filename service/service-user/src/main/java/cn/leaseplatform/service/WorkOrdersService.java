package cn.leaseplatform.service;

import cn.leaseplatform.entity.User;
import cn.leaseplatform.entity.WorkOrders;
import cn.leaseplatform.vo.UserWorkOrdersVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 梁歪歪
 * @since 2021-03-31
 */
public interface WorkOrdersService extends IService<WorkOrders> {
    /**
     * 获取用户工单信息
     * @param page
     * @return
     */
    Page<UserWorkOrdersVo> getUserWorkOrders(Page<UserWorkOrdersVo> page,Integer userId);

    /**
     * 用户工单条件查询
     * @param page
     * @return
     */
    Page<UserWorkOrdersVo> pageQuery(Page<UserWorkOrdersVo> page);
}
