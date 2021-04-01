package cn.leaseplatform.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author 梁歪歪
 * @since 2021-03-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_manufacturer_commodity")
@ApiModel(value="ManufacturerCommodity对象", description="")
public class ManufacturerCommodity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品编号")
    @TableId(value = "Commodity_id", type = IdType.AUTO)
    private Long commodityId;

    @ApiModelProperty(value = "商品图片")
    private String url;

    @ApiModelProperty(value = "商品名称")
    @TableField("Commodity_name")
    private String commodityName;

    @ApiModelProperty(value = "商品价格")
    @TableField("Commodity_price")
    private Integer commodityPrice;

    @ApiModelProperty(value = "商品详情")
    private String productDetails;

    @ApiModelProperty(value = "厂商编号")
    @TableField("Manufacturer_id")
    private Integer manufacturerId;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;
}
