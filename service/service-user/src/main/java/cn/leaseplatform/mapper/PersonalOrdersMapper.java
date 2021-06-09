package cn.leaseplatform.mapper;

import cn.leaseplatform.entity.PersonalOrders;
import cn.leaseplatform.vo.UserPersonalOrderVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 梁歪歪
 * @since 2021-03-31
 */
public interface PersonalOrdersMapper extends BaseMapper<PersonalOrders> {

    @Select("SELECT tp.`order_id`,tm.`Commodity_id`,tm.`url`,tm.`product_details`,tp.`Product_quantity`,tp.`Amount`,tp.`create_time`,tp.`update_time` FROM tb_manufacturer_commodity tm,tb_personal_orders tp WHERE tm.`Commodity_id` = tp.`Commodity_id` ORDER BY tp.`update_time` DESC")
    IPage<UserPersonalOrderVo> getUserPersonalOrders(Page<UserPersonalOrderVo> page, Integer userId);

    @Insert("INSERT INTO tb_personal_orders(Product_quantity,user_id,Commodity_id,Amount) VALUES (#{productQuantity},#{userId},#{commodityId},#{Amount})")
    int insert(UserPersonalOrderVo userPersonalOrderVo);
}
