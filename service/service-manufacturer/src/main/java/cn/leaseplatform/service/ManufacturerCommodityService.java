package cn.leaseplatform.service;

import cn.leaseplatform.entity.ManufacturerCommodity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 此处留名QCS
 * @since 2021-03-24
 */
public interface ManufacturerCommodityService extends IService<ManufacturerCommodity> {
    public List<ManufacturerCommodity> getByManId(Long Manid);
}
