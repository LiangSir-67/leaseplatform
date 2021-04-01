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
@TableName("tb_personal_orders")
@ApiModel(value="PersonalOrders对象", description="")
public class PersonalOrders implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单编号")
    @TableId(value = "order_id", type = IdType.AUTO)
    private Long orderId;

    @ApiModelProperty(value = "商品数量")
    @TableField("Product_quantity")
    private Integer productQuantity;

    @ApiModelProperty(value = "用户编号")
    private Long userId;

    @ApiModelProperty(value = "商品编号")
    @TableField("Commodity_id")
    private Long commodityId;

    @ApiModelProperty(value = "金额")
    @TableField("Amount")
    private Integer Amount;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;


}
