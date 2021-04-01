package cn.leaseplatform.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: 梁歪歪 <1732178815@qq.com>
 * @Description: blog <liangyy.cn>
 * @Create 2021-03-31-15:46
 */
@ApiModel(value = "用户个人订单对象",description = "用户个人订单对象")
@Data
public class UserPersonalOrderVo implements Serializable {

    @ApiModelProperty(value = "订单编号")
    @TableId(value = "order_id", type = IdType.AUTO)
    private Long orderId;

    @ApiModelProperty(value = "商品编号")
    @TableField("Commodity_id")
    private Long commodityId;

    @ApiModelProperty(value = "商品图片")
    private String url;

    @ApiModelProperty(value = "商品详情")
    private String productDetails;

    @ApiModelProperty(value = "商品数量")
    @TableField("Product_quantity")
    private Integer productQuantity;

    @ApiModelProperty(value = "商品价格")
    @TableField("Commodity_price")
    private Integer commodityPrice;

    @ApiModelProperty(value = "用户编号")
    private Long userId;

    @ApiModelProperty(value = "租金")
    @TableField("Amount")
    private Integer Amount;
}
