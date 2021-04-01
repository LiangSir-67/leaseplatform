package cn.leaseplatform.mapper;

import cn.leaseplatform.entity.ManorderVo;
import cn.leaseplatform.entity.ManufacturerOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 此处留名QCS
 * @since 2021-03-23
 */
public interface ManufacturerOrderMapper extends BaseMapper<ManufacturerOrder> {

    @Select("SELECT m.order_id,u.user_id,u.address,u.telephone from tb_Manufacturer_order m JOIN tb_user u on m.user_id=u.user_id")
    public List<ManorderVo> getManorderVo();

}
