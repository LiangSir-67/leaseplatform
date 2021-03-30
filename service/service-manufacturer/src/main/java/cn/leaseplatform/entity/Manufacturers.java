package cn.leaseplatform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author 此处留名QCS
 * @since 2021-03-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_manufacturers")
@ApiModel(value="Manufacturers对象", description="")
public class Manufacturers implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "厂商编号")
    @TableId(value = "Manufacturer_id", type = IdType.AUTO)
    private Integer manufacturerId;

    @ApiModelProperty(value = "商户名称")
    @TableField("Businesses_name")
    private String businessesName;

    @ApiModelProperty(value = "商户密码")
    private String password;

    @ApiModelProperty(value = "商户图片")
    private String url;

    @ApiModelProperty(value = "商户简介")
    @TableField("Businesses_intro")
    private String businessesIntro;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;


}
