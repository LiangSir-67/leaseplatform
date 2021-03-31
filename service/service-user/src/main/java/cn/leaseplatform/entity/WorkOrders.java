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
@TableName("tb_work_orders")
@ApiModel(value="UserWorkOrders对象", description="用户工单实体对象")
public class WorkOrders implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "工单编号")
    @TableId(value = "workorder_id", type = IdType.AUTO)
    private Integer workorderId;

    @ApiModelProperty(value = "工单状态")
    private Integer status;

    @ApiModelProperty(value = "订单编号")
    private Integer orderNumber;

    @ApiModelProperty(value = "用户编号")
    private Integer userId;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;


}
