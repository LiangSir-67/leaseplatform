package cn.leaseplatform.mapper;

import cn.leaseplatform.entity.ManufacturerCommodity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 此处留名QCS
 * @since 2021-03-24
 */
public interface ManufacturerCommodityMapper extends BaseMapper<ManufacturerCommodity> {


    @Select("select * from tb_Manufacturer_Commodity where Manufacturer_id=#{Manid}")
    public List<ManufacturerCommodity> getByManId(Long Manid);

}
