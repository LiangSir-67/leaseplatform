package cn.leaseplatform.service.impl;

import cn.leaseplatform.entity.ManufacturerCommodity;
import cn.leaseplatform.mapper.ManufacturerCommodityMapper;
import cn.leaseplatform.service.ManufacturerCommodityService;
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
 * @since 2021-03-24
 */
@Service
public class ManufacturerCommodityServiceImpl extends ServiceImpl<ManufacturerCommodityMapper, ManufacturerCommodity> implements ManufacturerCommodityService {

    @Autowired
    private ManufacturerCommodityMapper manufacturerCommodityMapper;

    @Override
    public List<ManufacturerCommodity> getByManId(Long Manid){
        return manufacturerCommodityMapper.getByManId(Manid);


    }
}
