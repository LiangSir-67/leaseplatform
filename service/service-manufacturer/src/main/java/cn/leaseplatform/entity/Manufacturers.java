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
 * @author 梁歪歪
 * @since 2021-04-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_manufacturers")
@ApiModel(value="Manufacturers对象", description="")
public class Manufacturers implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "厂商编号")
    @TableId(value = "Manufacturer_id", type = IdType.AUTO)
    private Long manufacturerId;

    @ApiModelProperty(value = "商户头像")
    @TableField("Businesses_avatar")
    private String businessesAvatar;

    @ApiModelProperty(value = "商户名称")
    @TableField("Businesses_name")
    private String businessesName;

    @ApiModelProperty(value = "商户密码")
    private String password;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "法人姓名")
    @TableField("Corporate_name")
    private String corporateName;

    @ApiModelProperty(value = "营业执照编码")
    private String businessCode;

    @ApiModelProperty(value = "营业执照")
    @TableField("License_Picture")
    private String licensePicture;

    @ApiModelProperty(value = "商户简介")
    @TableField("Businesses_intro")
    private String businessesIntro;

    @ApiModelProperty(value = "原因")
    private String reason;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;


}
