package cn.leaseplatform;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 厂商启动类
 */
@SpringBootApplication
@MapperScan("cn.leaseplatform.mapper")
@ComponentScan(basePackages = {
        "cn.leaseplatform.controller",
        "cn.leaseplatform.service",
        "cn.leaseplatform.handler",
        "cn.leaseplatform.config"
})
@EnableSwagger2
public class ServiceManufacturerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceManufacturerApplication.class,args);
    }
}






