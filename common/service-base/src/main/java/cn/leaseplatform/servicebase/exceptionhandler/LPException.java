package cn.leaseplatform.servicebase.exceptionhandler;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: 梁歪歪 <1732178815@qq.com>
 * @Description: blog <liangyy.cn>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LPException extends RuntimeException {
    @ApiModelProperty(value = "状态码")
    private Integer code;   // 状态码

    @ApiModelProperty(value = "异常信息")
    private String msg;     // 异常信息
}
