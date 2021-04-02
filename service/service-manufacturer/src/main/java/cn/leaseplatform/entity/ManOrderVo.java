package cn.leaseplatform.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;

import java.math.BigInteger;

@Data
@ApiModel(value = "订单对象",description = "订单对象")
public class ManOrderVo {

    @ApiModelProperty(value = "订单编号")
    private Long orderId;

    @ApiModelProperty(value = "用户编号")
    private Long userId;

    @ApiModelProperty(value = "收货人地址")
    private String address;

    @ApiModelProperty(value = "电话号码")
    private String telephone;

    @ApiModelProperty(value = "金额")
    private Integer Amount;

    @ApiModelProperty(value = "状态")
    private Integer status;



}
