package cn.leaseplatform.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel(value = "商家修改对象",description = "商家修改对象")
public class ManfactureVo {


    @ApiModelProperty(value = "商户名称")
    private String businessesName;

    @ApiModelProperty(value = "法人姓名")
    private String corporateName;

    @ApiModelProperty(value = "营业执照编码")
    private String businessCode;

    @ApiModelProperty(value = "营业执照")
    private String licensePicture;

    @ApiModelProperty(value = "商户简介")
    private String businessesIntro;




}
