package cn.leaseplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ServiceProotApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceProotApplication.class,args);
    }
}

