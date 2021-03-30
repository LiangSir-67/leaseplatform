package cn.leaseplatform.entity;


import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "注册对象",description = "注册对象")
public class RegisterVo {

    private String adminname;
    private String password;

}
