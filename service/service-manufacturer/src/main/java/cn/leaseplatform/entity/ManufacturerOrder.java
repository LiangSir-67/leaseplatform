package cn.leaseplatform.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @author 此处留名QCS
 * @since 2021-03-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_manufacturer_order")
@ApiModel(value="ManufacturerOrder对象", description="")
public class ManufacturerOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单编号")
    @TableId(value = "order_id", type = IdType.AUTO)
    private Integer orderId;

    @ApiModelProperty(value = "状态")
    @TableField("State")
    private Integer State;

    @ApiModelProperty(value = "用户编号")
    private Integer userId;

    @ApiModelProperty(value = "厂商编号")
    @TableField("Manufacturers_id")
    private Integer manufacturersId;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;


}
