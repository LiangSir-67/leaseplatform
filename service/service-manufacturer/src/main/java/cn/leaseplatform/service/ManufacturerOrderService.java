package cn.leaseplatform.service;

import cn.leaseplatform.entity.ManOrderVo;
import cn.leaseplatform.entity.ManufacturerOrder;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 此处留名QCS
 * @since 2021-03-23
 */
public interface ManufacturerOrderService extends IService<ManufacturerOrder> {

    public List<ManOrderVo> getManorderVo(Long Manid);

    public ManOrderVo getOrdertInfoByOrderId(Long Manid,Long orderId);

}
