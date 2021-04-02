package cn.leaseplatform.entity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "登录对象",description = "登陆对象")
public class LoginVo {
    @ApiModelProperty(value = "商家用户名")
    private String businessesName;
    @ApiModelProperty(value = "密码")
    private String password;
}
