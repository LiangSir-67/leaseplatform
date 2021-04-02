package cn.leaseplatform.service.impl;

import cn.leaseplatform.entity.ManOrderVo;
import cn.leaseplatform.entity.ManufacturerOrder;
import cn.leaseplatform.mapper.ManufacturerOrderMapper;
import cn.leaseplatform.service.ManufacturerOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 此处留名QCS
 * @since 2021-03-23
 */
@Service
public class ManufacturerOrderServiceImpl extends ServiceImpl<ManufacturerOrderMapper, ManufacturerOrder> implements ManufacturerOrderService {

    /*@Autowired
    private ManufacturerOrderMapper manufacturerOrderMapper;

    public R getOrderInfo(){
        List<ManufacturerOrder> ManufacturerOrders= manufacturerOrderMapper.selectList(null);
        return R.ok().data("manufacturersInfo",ManufacturerOrders);


    }*/

    @Autowired
    private ManufacturerOrderMapper manufacturerOrderMapper;

    @Override
    public List<ManOrderVo> getManorderVo(Long Manid){
        return manufacturerOrderMapper.getManorderVo(Manid);

    }
    @Override
    public ManOrderVo getOrdertInfoByOrderId(Long Manid,Long orderId){
        return manufacturerOrderMapper.getOrdertInfoByOrderId(Manid,orderId);
    }

}
