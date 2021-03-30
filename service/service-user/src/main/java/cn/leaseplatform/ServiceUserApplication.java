package cn.leaseplatform;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 用户启动类
 */
@SpringBootApplication
@MapperScan("cn.leaseplatform.mapper")
@ComponentScan(basePackages = {
        "cn.leaseplatform.config",
        "cn.leaseplatform.controller",
        "cn.leaseplatform.service",
        "cn.leaseplatform.handler"
})
@EnableSwagger2
public class ServiceUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceUserApplication.class,args);
    }
}



