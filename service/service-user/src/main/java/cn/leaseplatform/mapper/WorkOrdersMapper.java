package cn.leaseplatform.mapper;

import cn.leaseplatform.entity.WorkOrders;
import cn.leaseplatform.vo.UserWorkOrdersVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javafx.scene.control.Pagination;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 梁歪歪
 * @since 2021-03-31
 */
public interface WorkOrdersMapper extends BaseMapper<WorkOrders> {

    @Select("SELECT wo.`workorder_id`,u.`user_id`,u.`username`,u.`telephone`,u.`address`,wo.`status` FROM tb_user u,tb_work_orders wo WHERE u.`user_id` = wo.`user_id` ORDER BY wo.`update_time` DESC")
    List<UserWorkOrdersVo> getUserWorkOrders(Page<UserWorkOrdersVo> page);
}
