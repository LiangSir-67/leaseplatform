package cn.leaseplatform.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @author 此处留名QCS
 * @since 2021-03-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_manufacturer_work_order")
@ApiModel(value="ManufacturerWorkOrder对象", description="")
public class ManufacturerWorkOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "工单编号")
    @TableId(value = "wordorder_id", type = IdType.AUTO)
    private Integer wordorderId;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "订单编号")
    private Integer orderCode;

    @ApiModelProperty(value = "用户编号")
    private Integer userId;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;


}
