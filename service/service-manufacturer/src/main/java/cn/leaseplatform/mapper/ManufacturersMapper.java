package cn.leaseplatform.mapper;

import cn.leaseplatform.entity.ManfactureVo;
import cn.leaseplatform.entity.Manufacturers;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 此处留名QCS
 * @since 2021-03-29
 */
public interface ManufacturersMapper extends BaseMapper<Manufacturers> {

    @Select("select * from tb_Manufacturers where Manufacturer_id=#{Id}")
    public Manufacturers getLoginMan(@Param("Id") String Id);


    @Update("update tb_Manufacturers set Businesses_avatar = #{url} where Manufacturer_id = #{BusiId}")
    public void editManPicture(String url,Long BusiId);



    public void editManFctureInfo( @Param("manfactureVo") ManfactureVo manfactureVo, Long BusiId);

}
