package cn.leaseplatform.mapper;

import cn.leaseplatform.entity.ManOrderVo;
import cn.leaseplatform.entity.ManufacturerOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
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


    public List<ManOrderVo> getManorderVo(Long Manid);

    public ManOrderVo getOrdertInfoByOrderId(Long Manid,Long orderId);

}
