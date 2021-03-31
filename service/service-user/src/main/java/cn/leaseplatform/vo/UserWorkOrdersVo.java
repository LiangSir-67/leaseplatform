package cn.leaseplatform.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author: 梁歪歪 <1732178815@qq.com>
 * @Description: blog <liangyy.cn>
 * @Create 2021-03-31-14:27
 */
@ApiModel(value = "用户工单对象",description = "用户工单对象")
@Data
public class UserWorkOrdersVo implements Serializable {

    @ApiModelProperty(value = "订单编号")
    private String workorderId;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "收货人")
    private String username;

    @ApiModelProperty(value = "收货人电话号码")
    private String telephone;

    @ApiModelProperty(value = "收货人地址")
    private String address;

    @ApiModelProperty(value = "工单状态")
    private Integer status;
}
